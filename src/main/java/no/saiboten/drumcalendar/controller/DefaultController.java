package no.saiboten.drumcalendar.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.saiboten.drumcalendar.day.DayPostgres;
import no.saiboten.drumcalendar.day.DayService;
import no.saiboten.drumcalendar.user.CalendarUser;
import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;
import no.saiboten.drumcalendar.user.UserStatistics;
import no.saiboten.drumcalendar.utils.StatisticsService;
import no.saiboten.drumcalendar.winner.WinnerService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

	private DayService dayService;

	private LoggedInRequestHolder loggedIn;

	private CalendarUserService userService;

	private StatisticsService statsService;

	private WinnerService winnerService;
		
	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	public DefaultController(DayService dayService,
			LoggedInRequestHolder loggedIn, CalendarUserService userService,
			StatisticsService statsService, WinnerService winnerService) {
		this.dayService = dayService;
		this.loggedIn = loggedIn;
		this.userService = userService;
		this.statsService = statsService;
		this.winnerService = winnerService;
	}

	@RequestMapping(value="/")
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

		Map<DayPostgres, CalendarUser> winnerMap = winnerService.getWinners();
		Map<Long, CalendarUser> winners = new HashMap<Long, CalendarUser>();
		Map<Long, DayPostgres> days = new HashMap<Long, DayPostgres>();
		List<Long> longDay = new ArrayList<Long>();

		for (DayPostgres day : winnerMap.keySet()) {
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

	
	
	
	
	
	
}
