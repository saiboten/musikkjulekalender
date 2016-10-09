package no.saiboten.drumcalendar.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;
import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;

import org.keycloak.KeycloakSecurityContext;
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

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		
		KeycloakSecurityContext sc = (KeycloakSecurityContext) request
	    .getAttribute(KeycloakSecurityContext.class.getName());
		
		logger.debug("Security context: ", sc);
				
		if (loggedIn.isLoggedIn()) {
			logger.debug("User is logged in!");
			CalendarUserPostgres user = userService.getUser(loggedIn.getUserName());
			if (user == null) {
				logger.debug("User does not exist in db. Creating user in db!");
				user = new CalendarUserPostgres();
				user.setUserName(loggedIn.getUserName());
				userService.putUser(user);
			}

			loggedIn.setCalendarUser(user);
		}
		return true;
	}
}
