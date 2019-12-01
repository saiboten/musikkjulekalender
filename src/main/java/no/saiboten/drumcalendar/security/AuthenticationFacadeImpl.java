package no.saiboten.drumcalendar.security;

import java.util.Map;

import no.saiboten.drumcalendar.rest.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {
	
	private final Logger logger = LoggerFactory.getLogger(AuthenticationFacadeImpl.class);
	
	@Override
	public String getEmail() {
		
		
		String email = null;
		SecurityContext sc = SecurityContextHolder.getContext();
		if (sc.getAuthentication() instanceof OAuth2Authentication) {
			OAuth2Authentication auth = (OAuth2Authentication) sc.getAuthentication();
			
			Map<String, String> details = (Map<String, String>) auth
					.getUserAuthentication().getDetails();
			
			logger.warn("Details: " + details);
			
			email = details.get("email");
			
			if(email != null) {
				email = details.get("id") + "@unknown.no";
			}
			
		}
		

		return email;
	}
}
