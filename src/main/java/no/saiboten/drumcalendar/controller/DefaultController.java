package no.saiboten.drumcalendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

	public DefaultController() {

	}

	@RequestMapping(value="/")
	public ModelAndView frontpage() {
		ModelAndView mav = new ModelAndView("main");
		return mav;
	}

	@RequestMapping("/om")
	public ModelAndView om() {
		ModelAndView mav = new ModelAndView("om");
		mav.getModel().put("about","active");
		return mav;
	}
	
}
