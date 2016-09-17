package no.saiboten.drumcalendar.service.impl;

import java.io.IOException;

import no.saiboten.drumcalendar.service.GoogleLoginService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;
import no.saiboten.drumcalendar.utils.GooglePlusLoginResults;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;
import com.google.api.services.oauth2.model.Userinfoplus;

@Service
public class GoogleLoginServiceImpl implements GoogleLoginService {

	private final Logger LOGGER = Logger.getLogger(getClass());
	private LoggedInRequestHolder loggedInRequestHolder;

	@Autowired
	public GoogleLoginServiceImpl(LoggedInRequestHolder loggedInRequestHolder) {
		this.loggedInRequestHolder = loggedInRequestHolder;
	}

	@Override
	public GooglePlusLoginResults login(String oneTimeCode) {
		if(loggedInRequestHolder.isLoggedIn()) {
			LOGGER.info("User is already logged in. No need to do anything");
			return GooglePlusLoginResults.SUCCESS;
		}
		else {
			try {
				// Upgrade the authorization code into an access and refresh token.
				GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
						new NetHttpTransport(),
						new JacksonFactory(),
						"814247292614-kvbdepicmv5sbk5ufocb5lf7agcqf907.apps.googleusercontent.com",
						"6NxDlSiDFudyAZ2ejnEOXDfg", oneTimeCode, "postmessage")
						.execute();

				// Create a credential representation of the token data.
				GoogleCredential credential = new GoogleCredential.Builder()
						.setJsonFactory(new JacksonFactory())
						.setTransport(new NetHttpTransport())
						.setClientSecrets(
								"814247292614-kvbdepicmv5sbk5ufocb5lf7agcqf907.apps.googleusercontent.com",
								"6NxDlSiDFudyAZ2ejnEOXDfg").build()
						.setFromTokenResponse(tokenResponse);
				

				// Check that the token is valid.
				Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(),
						new JacksonFactory(), credential).build();
				Tokeninfo tokenInfo = oauth2.tokeninfo()
						.setAccessToken(credential.getAccessToken()).execute();
				// If there was an error in the token info, abort.
				if (tokenInfo.containsKey("error")) {
					return GooglePlusLoginResults.TOKEN_ERROR;
				}
				// Make sure the token we got is for our app.
				else if (!tokenInfo
						.getIssuedTo()
						.equals("814247292614-kvbdepicmv5sbk5ufocb5lf7agcqf907.apps.googleusercontent.com")) {
					LOGGER.error("Token's client ID does not match app's.");
					return GooglePlusLoginResults.TOKEN_MISMATCH;
				}
				// Store the token in the session for later use.
				if (setUserLoggedIn(oauth2)) {
					return GooglePlusLoginResults.SUCCESS;
				} else {
					return GooglePlusLoginResults.GET_PROFILE_DATA_FAILED;
				}

			} catch (TokenResponseException e) {
				LOGGER.error("Failed to upgrade the authorization code.");
				return GooglePlusLoginResults.AUTH_CODE_UPDATE_ERROR;
			} catch (IOException e) {
				LOGGER.error("Failed to read token data from Google. "
						+ e.getMessage());
				return GooglePlusLoginResults.TOKEN_READ_ERROR;
			}
		}

	}

	private boolean setUserLoggedIn(Oauth2 oauth2) {
		boolean success = false;
		Userinfoplus userinfo;
		
		try {
			userinfo = oauth2.userinfo().get().execute();
			LOGGER.debug("Retrieving username" + userinfo.getEmail());
			loggedInRequestHolder.setUserName(userinfo.getEmail());
			loggedInRequestHolder.setLoggedIn(true);
			success = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return success;
	}

}
