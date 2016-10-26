package no.saiboten.drumcalendar.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

	private final Path rootLocation;
	
	private final static Logger logger = org.slf4j.LoggerFactory.getLogger(StorageService.class);

	@Autowired
	public StorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	public void store(MultipartFile file, String fileName) {
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file " + fileName);
			}
			Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
		}
	}

	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	public Resource loadAsResource(String filename) {
		try {
			logger.debug("File: " + filename);
			Path file = load(filename);
			logger.debug("Path: " + filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				logger.debug("File exists and is readable: " + filename);
				return resource;
			} else {
				throw new RuntimeException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Could not read file: " + filename, e);
		}
	}

}
