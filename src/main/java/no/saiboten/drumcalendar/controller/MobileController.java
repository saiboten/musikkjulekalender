package no.saiboten.drumcalendar.controller;

import javax.servlet.http.HttpServletRequest;

import no.saiboten.drumcalendar.user.LoggedInRequestHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MobileController {

	private LoggedInRequestHolder loggedIn;

	@Autowired
	public MobileController(LoggedInRequestHolder loggedIn) {
		this.loggedIn = loggedIn;
	}

	@RequestMapping("/m")
	public ModelAndView getMain() {
		ModelAndView mav = new ModelAndView("mobile/overview");
		if (loggedIn.isLoggedIn()) {
			mav.addObject("loggedIn", true);
		} else {
			mav.addObject("loggedIn", false);
		}
		return mav;
	}

	@RequestMapping("/m/login")
	public ModelAndView logIn(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("mobile/login");	
		if (loggedIn.isLoggedIn()) {
			mav.addObject("loggedIn", true);
		} else {
			mav.addObject("loggedIn", false);
		}

		return mav;
	}

	@RequestMapping("/m/overview")
	public String getOverview() {
		return "mobile/main";
	}
}
