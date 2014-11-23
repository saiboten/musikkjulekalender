package no.saiboten.drumcalendar.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;

@Controller
public class DefaultController {

	private DayService dayService;

	private LoggedInRequestHolder loggedIn;

	private CalendarUserService userService;

	private StatisticsService statsService;

	private WinnerService winnerService;
	
	private GoogleLoginService googleLoginService;
	
	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	public DefaultController(DayService dayService,
			LoggedInRequestHolder loggedIn, CalendarUserService userService,
			StatisticsService statsService, WinnerService winnerService, GoogleLoginService googleLoginService) {
		this.dayService = dayService;
		this.loggedIn = loggedIn;
		this.userService = userService;
		this.statsService = statsService;
		this.winnerService = winnerService;
		this.googleLoginService = googleLoginService;
	}

	@RequestMapping("")
	public ModelAndView start() {
		ModelAndView mav = new ModelAndView("main");

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
	public String om() {
		return "om";
	}

	@RequestMapping("/overview")
	public ModelAndView overview() {
		ModelAndView mav = new ModelAndView("overview");
		mav.addObject("now", Calendar.getInstance().getTimeInMillis());
		mav.addObject("days", dayService.getDays());
		mav.addObject("loggedIn", loggedIn.isLoggedIn());
		LOGGER.debug("Is the user logged in?" + loggedIn.isLoggedIn());
		if (loggedIn.getCalendarUser() != null) {
			LOGGER.debug("Do we have the calendar user? " + loggedIn.getCalendarUser());
			mav.addObject("answers", loggedIn.getCalendarUser().getAnswers());
		}

		List<CalendarUser> users = userService.getAllUsers();
		if (users != null) {
			mav.addObject("numberOfUsers", users.size());
		}

		mav.addObject("statistics", statsService.getStatistics());
		return mav;
	}

	@RequestMapping("/logmeon")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("login");
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
}
