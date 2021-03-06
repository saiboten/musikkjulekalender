package no.saiboten.drumcalendar.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;
import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;

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

	private LoggedInRequestHolder loggedIn;

	private CalendarUserService userService;

	private AuthenticationFacade authenticationFacade;
	
	@Autowired
	public SecurityInterceptor(LoggedInRequestHolder loggedIn, CalendarUserService userService, AuthenticationFacade authenticationFacade) {
		this.loggedIn = loggedIn;
		this.userService = userService;
		this.authenticationFacade = authenticationFacade;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		if (authenticationFacade.getEmail() != null) {
			String email = authenticationFacade.getEmail();
			String nickName = authenticationFacade.getEmail();
			if (email != null) {
				loggedIn.setLoggedIn(true);
				loggedIn.setUserName(email);
				loggedIn.setNickName(nickName);
			}
		} else {
			loggedIn.setLoggedIn(false);
			loggedIn.setUserName(null);
			loggedIn.setNickName(null);
		}

		if (loggedIn.isLoggedIn()) {
			logger.debug("User is logged in, user is :"
					+ loggedIn.getUserName());
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
