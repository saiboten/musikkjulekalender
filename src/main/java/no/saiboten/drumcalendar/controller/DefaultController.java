package no.saiboten.drumcalendar.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import no.saiboten.drumcalendar.rest.RestController;
import no.saiboten.drumcalendar.storage.StorageService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;

@Controller
public class DefaultController {

	private final Logger logger = LoggerFactory.getLogger(RestController.class);
	private LoggedInRequestHolder loggedInRequestHolder;
	private StorageService storageService;

	@Autowired
	public DefaultController(LoggedInRequestHolder loggedInRequestHolder,
			StorageService storageService) {
		this.loggedInRequestHolder = loggedInRequestHolder;
		this.storageService = storageService;
	}

	@RequestMapping(value = "/")
	public String frontpage(Model model) {
		logger.debug("Request for front page");
		model.addAttribute("loggedin", loggedInRequestHolder.isLoggedIn());
		return "main";
	}
	
	@RequestMapping(value = "/om")
	public String about(Model model) {
		logger.debug("Request for front page");
		model.addAttribute("loggedin", loggedInRequestHolder.isLoggedIn());
		return "main";
	}
	
	@RequestMapping(value = "/login")
	public String login(Model model) {
		logger.debug("Request for front page");
		model.addAttribute("loggedin", loggedInRequestHolder.isLoggedIn());
		return "main";
	}

	@RequestMapping(value = "/logout")
	public RedirectView secure(HttpServletRequest request) {
		logger.debug("User is requesting to log out");
		try {
			request.logout();
			logger.debug("Are we logged out? Hope so.");
		} catch (ServletException e) {
			logger.error("Logout failed: " + e.getMessage());
		}
		return new RedirectView("/");
	}
	
	@RequestMapping(value = "/loginadmin")
	public String AdminLogin(HttpServletRequest request) {
		return "loginadmin";
	}

	@GetMapping("/songs/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		logger.debug("Request for: " + filename);

		Resource file = storageService.loadAsResource(filename);
		logger.debug("File: " + file);
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}