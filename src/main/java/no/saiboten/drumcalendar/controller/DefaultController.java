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
	public DefaultController(LoggedInRequestHolder loggedInRequestHolder, StorageService storageService) {
		this.loggedInRequestHolder = loggedInRequestHolder;
		this.storageService = storageService;
	}

	@RequestMapping(value = "/")
	public String frontpage(Model model) {
		logger.debug("Request for front page");
		model.addAttribute("loggedin", loggedInRequestHolder.isLoggedIn());
		return "main";
	}

	@RequestMapping(value = "/secure")
	public RedirectView login(Model model) {
		logger.debug("/secure is secured - will be a login");
		return new RedirectView("/");
	}

	@RequestMapping(value = "/logout")
	public RedirectView secure(HttpServletRequest request) {
		logger.debug("Request for front page");
		try {
			request.logout();
		} catch (ServletException e) {
			logger.error("Logout failed: " + e.getMessage());
		}
		return new RedirectView("/");
	}

	@RequestMapping("/om")
	public String om(Model model) {
		model.addAttribute("loggedin", loggedInRequestHolder.isLoggedIn());
		return "om";
	}
	
	 @GetMapping("/songs/{filename:.+}")
	    @ResponseBody
	    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

	        Resource file = storageService.loadAsResource(filename);
	        return ResponseEntity
	                .ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
	                .body(file);
	    }


}