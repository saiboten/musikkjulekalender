package no.saiboten.drumcalendar.controller;

import no.saiboten.drumcalendar.day.DayService;
import no.saiboten.drumcalendar.toplist.TopListService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;
import no.saiboten.drumcalendar.user.UserResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
public class JsonController {

	private DayService dayService;

	private LoggedInRequestHolder loggedIn;

	private UserResultService userResultService;

	private TopListService topListService;
	

	@Autowired
	public JsonController(DayService dayService, LoggedInRequestHolder loggedIn, UserResultService userResultService, TopListService topListService) {
		this.dayService = dayService;
		this.loggedIn = loggedIn;
		this.userResultService = userResultService;
		this.topListService = topListService;
	}

	@RequestMapping("/days.json")
	public ModelAndView getDays() {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJackson2JsonView());
		mav.addObject("days", dayService.getSpoilerFreeDays());
		mav.addObject("user", loggedIn.getCalendarUser());
		mav.addObject("isLoggedIn", loggedIn.isLoggedIn());
		mav.addObject("date", System.currentTimeMillis());
		mav.addObject("userResult", userResultService.getUserResults());
		mav.addObject("topList", topListService.getTopList());
		return mav;
	}

	@RequestMapping("/loggedin.json")
	public ModelAndView isLoggedIn() {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJackson2JsonView());
		mav.addObject("isLoggedIn", loggedIn.isLoggedIn());
		return mav;
	}
}
