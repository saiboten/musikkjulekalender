package no.saiboten.drumcalendar.security;

import java.security.Principal;
import java.util.Map;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {
	
	@Override
	public String getEmail() {
		
		
		String email = null;
		SecurityContext sc = SecurityContextHolder.getContext();
		if (sc.getAuthentication() instanceof OAuth2Authentication) {
			OAuth2Authentication auth = (OAuth2Authentication) sc.getAuthentication();
			Map<String, String> details = (Map<String, String>) auth
					.getUserAuthentication().getDetails();
			email = details.get("email");
		}

		return email;
	}
}
