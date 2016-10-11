package no.saiboten.drumcalendar.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import no.saiboten.drumcalendar.rest.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DefaultController {

	private final Logger logger = LoggerFactory.getLogger(RestController.class);

	@RequestMapping(value="/" )
	public String frontpage(Model model) {
		logger.debug("Request for front page");
		return "main";
	}
	
	@RequestMapping(value="/secure" )
	public RedirectView login(Model model) {
		logger.debug("/secure is secured - will be a login");
		return new RedirectView("/");
	}
	
	@RequestMapping(value="/logout" )
	public RedirectView secure(HttpServletRequest request) {
		logger.debug("Request for front page");
		try {
			request.logout();
		} catch (ServletException e) {
			logger.error("Logout failed: "+e.getMessage());
		}
		return new RedirectView("/");
	}
	

	@RequestMapping("/om")
	public String om() {
		return "om";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login2";
	}
	
}