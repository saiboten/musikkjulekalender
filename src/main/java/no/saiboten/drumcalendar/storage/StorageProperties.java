package no.saiboten.drumcalendar.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class StorageProperties {
	/**
	 * Folder location for storing files
	 */
	@Value("${upload-dir}")
	private String location = "replace";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
