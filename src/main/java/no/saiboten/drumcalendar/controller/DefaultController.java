package no.saiboten.drumcalendar.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.saiboten.drumcalendar.day.Day;
import no.saiboten.drumcalendar.day.DayService;
import no.saiboten.drumcalendar.service.FacebookLoginService;
import no.saiboten.drumcalendar.service.GoogleLoginService;
import no.saiboten.drumcalendar.service.WinnerService;
import no.saiboten.drumcalendar.user.CalendarUser;
import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;
import no.saiboten.drumcalendar.user.UserStatistics;
import no.saiboten.drumcalendar.utils.GooglePlusLoginResults;
import no.saiboten.drumcalendar.utils.StatisticsService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
public class DefaultController {

	private DayService dayService;

	private LoggedInRequestHolder loggedIn;

	private CalendarUserService userService;

	private StatisticsService statsService;

	private WinnerService winnerService;
	
	private GoogleLoginService googleLoginService;
	
	private final Logger LOGGER = Logger.getLogger(getClass());

	private FacebookLoginService facebookLoginService;

	@Autowired
	public DefaultController(DayService dayService,
			LoggedInRequestHolder loggedIn, CalendarUserService userService,
			StatisticsService statsService, WinnerService winnerService, GoogleLoginService googleLoginService, FacebookLoginService facebookLoginService) {
		this.dayService = dayService;
		this.loggedIn = loggedIn;
		this.userService = userService;
		this.statsService = statsService;
		this.winnerService = winnerService;
		this.googleLoginService = googleLoginService;
		this.facebookLoginService = facebookLoginService;
	}

	@GetMapping(value="")
	public ModelAndView start() {
		ModelAndView mav = new ModelAndView("main");
		
		mav.addObject("frontpage", "active");

		mav.addObject("now", Calendar.getInstance().getTimeInMillis());

		CalendarUser user = loggedIn.getCalendarUser();

		mav.addObject("today", dayService.getToday());
		mav.addObject("loggedIn", loggedIn.isLoggedIn());
		mav.addObject("user", user);

		List<CalendarUser> bestUsers = statsService.getBestUsers();
		if (bestUsers != null && bestUsers.size() > 0) {
			mav.addObject("bestUsers", bestUsers);
		}

		if (user != null) {
			UserStatistics userStats = userService.getUserStatistics(user
					.getUserName());
			mav.addObject("userStats", userStats);
		}

		Map<Day, CalendarUser> winnerMap = winnerService.getWinners();
		Map<Long, CalendarUser> winners = new HashMap<Long, CalendarUser>();
		Map<Long, Day> days = new HashMap<Long, Day>();
		List<Long> longDay = new ArrayList<Long>();

		for (Day day : winnerMap.keySet()) {
			winners.put(day.getRevealDateAsInt(), winnerMap.get(day));
			days.put(day.getRevealDateAsInt(), day);
			longDay.add(day.getRevealDateAsInt());
		}

		Collections.sort(longDay);
		mav.addObject("winners", winners);
		mav.addObject("days", days);
		mav.addObject("longDays", longDay);

		return mav;
	}

	@RequestMapping("/om")
	public ModelAndView om() {
		ModelAndView mav = new ModelAndView("om");
		mav.getModel().put("about","active");
		return mav;
	}

	@RequestMapping("/logmeon")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("logmein", "active");
		return mav;
	}

	@RequestMapping("/plus")
	public ModelAndView pluslogin(HttpServletRequest request,
			HttpServletResponse response, @RequestBody String code) {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJackson2JsonView());
		LOGGER.debug("Login in using Google plus");
		GooglePlusLoginResults googlePlusLoginResults = googleLoginService.login(code);
		mav.addObject("result", googlePlusLoginResults.toString());
		return mav;
		
	}
	
	@RequestMapping(name="/facebooklogin", method=RequestMethod.POST)
	public ModelAndView facebookLoginService(HttpServletRequest request,
			HttpServletResponse response, @RequestBody String accessToken) {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJackson2JsonView());
		
		LOGGER.debug("Login in using Facebook");
		mav.addObject("result", facebookLoginService.login(accessToken));
		return mav;
		
	}
	
	
	
	
	
}
