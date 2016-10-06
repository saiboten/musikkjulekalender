package no.saiboten.drumcalendar.controller;

import no.saiboten.drumcalendar.rest.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	private final Logger logger = LoggerFactory.getLogger(RestController.class);

	@RequestMapping(value="/" )
	public String frontpage(Model model) {
		logger.debug("Request for front page");
		return "main";
	}

	@RequestMapping("/om")
	public String om() {
		return "om";
	}
	
}