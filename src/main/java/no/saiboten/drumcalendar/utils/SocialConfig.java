package no.saiboten.drumcalendar.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
public class SocialConfig implements SocialConfigurer {

	@Value("${facebook.secret}")
	private String secret;

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig,
			Environment env) {
		cfConfig.addConnectionFactory(new FacebookConnectionFactory(
				"258625720859950", secret));
	}
	

	@Override
	public UserIdSource getUserIdSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(
			ConnectionFactoryLocator arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}