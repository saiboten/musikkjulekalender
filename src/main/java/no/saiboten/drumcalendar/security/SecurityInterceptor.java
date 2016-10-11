package no.saiboten.drumcalendar.security;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;
import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter implements
		HandlerInterceptor {

	Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

	@Autowired
	CalendarUserService userService;

	public SecurityInterceptor() {

	}

	@Autowired
	private LoggedInRequestHolder loggedIn;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {

		final Principal userPrincipal = request.getUserPrincipal();
		
		logger.debug("URL: " + request.getRequestURI());

		logger.debug("User principal: " + userPrincipal);
		
		if (userPrincipal instanceof KeycloakAuthenticationToken) {
			logger.debug("Its instance of alright");
			KeycloakAuthenticationToken kp = (KeycloakAuthenticationToken) userPrincipal;
			String email = kp.getAccount().getKeycloakSecurityContext().getToken().getEmail();

			if(email != null) {
				loggedIn.setLoggedIn(true);
				loggedIn.setUserName(email);
				loggedIn.setNickName(kp.getAccount().getKeycloakSecurityContext().getToken().getPreferredUsername());
			}
		}

		if (loggedIn.isLoggedIn()) {
			logger.debug("User is logged in, user is :" +loggedIn.getUserName());
			CalendarUserPostgres user = userService.getUser(loggedIn
					.getUserName());
			if (user == null) {
				logger.debug("User does not exist in db. Creating user in db!");
				user = new CalendarUserPostgres();
				user.setUserName(loggedIn.getUserName());
				user.setNickName(loggedIn.getNickName());
				userService.putUser(user);
			}

			loggedIn.setCalendarUser(user);
		}
		return true;
	}
}
