package no.saiboten.drumcalendar.service.impl;

import java.io.IOException;

import no.saiboten.drumcalendar.service.FacebookLoginService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

@Service
public class FacebookLoginServiceImpl implements FacebookLoginService {

	private final Logger LOGGER = Logger.getLogger(getClass());

	private LoggedInRequestHolder loggedInRequestHolder;

	@Autowired
	public FacebookLoginServiceImpl(LoggedInRequestHolder loggedInRequestHolder) {
		this.loggedInRequestHolder = loggedInRequestHolder;
	}

	@Override
	public boolean login(String accessToken) {
		boolean success = false;
		
		if(loggedInRequestHolder.isLoggedIn()) {
			LOGGER.info("User is already logged in. No need to do anything");
		}
		else {
			Facebook facebook = new FacebookTemplate(accessToken);
			UserOperations userOperations = facebook.userOperations();

			String username = userOperations.getUserProfile().getEmail();
			if(StringUtils.isNotEmpty(username)) {
				LOGGER.debug("Facebook Username: " + username);
				loggedInRequestHolder.setUserName(username);
				loggedInRequestHolder.setLoggedIn(true);
				success = true;
			}
			else {
				LOGGER.warn("Facebook Username is empty! Something has gone wrong ...");
			}
		}
		
		
		return success;
	}

}
