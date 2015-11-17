package no.saiboten.drumcalendar.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import no.saiboten.drumcalendar.day.Day;
import no.saiboten.drumcalendar.day.DayService;
import no.saiboten.drumcalendar.service.HelperService;
import no.saiboten.drumcalendar.service.WinnerService;
import no.saiboten.drumcalendar.user.CalendarUser;
import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;
import no.saiboten.drumcalendar.utils.StatisticsService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AdminController {

	private CalendarUserService userService;

	private StatisticsService statsService;

	private WinnerService winnerService;

	private HelperService helperService;

	private DayService dayService;
	
	private Logger LOGGER = Logger.getLogger(getClass());

	private LoggedInRequestHolder loggedIn;
	
	@Autowired
	public AdminController(CalendarUserService userService, StatisticsService statsService,
			WinnerService winnerService, HelperService helperService, DayService dayService, LoggedInRequestHolder loggedIn) {
		this.userService = userService;
		this.statsService = statsService;
		this.winnerService = winnerService;
		this.helperService = helperService;
		this.dayService = dayService;
		this.loggedIn = loggedIn;
	}

	@RequestMapping(value = "/admin/resetday/{day}")
	public ModelAndView resetDay(@PathVariable(value = "day") Long day) {
		ModelAndView mav = new ModelAndView("answergiven");
		helperService.resetDay(day);
		return mav;
	}

	@RequestMapping(value = "/admin/deleteusers")
	public ModelAndView deleteUsers() {
		ModelAndView mav = new ModelAndView("answergiven");
		userService.deleteAllUsers();
		return mav;
	}

	@RequestMapping(value = "/admin/deletestatistics")
	public ModelAndView deleteStatistics() {
		ModelAndView mav = new ModelAndView("answergiven");
		statsService.deleteStatistics();
		return mav;
	}

	@RequestMapping(value = "/admin/users")
	public ModelAndView viewUsers() {
		ModelAndView mav = new ModelAndView("users");
		mav.addObject("users", userService.getAllUsers());
		mav.addObject("days", dayService.getDays());
		return mav;
	}
	
	@RequestMapping(value = "/admin/overview")
	public ModelAndView adminOverview() {
			ModelAndView mav = new ModelAndView("overviewadmin");
			mav.addObject("overview", "active");
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
				mav.addObject("users", users);
			}

			mav.addObject("statistics", statsService.getStatistics());
			return mav;
	}

	@RequestMapping("/admin/newwinner/{day}")
	public ModelAndView addWinner(@PathVariable("day") Long day) {
		ModelAndView mav = new ModelAndView("users");
		winnerService.addWinner(day);
		return mav;
	}

	@RequestMapping("/admin/fixsong/{mail}/{day}")
	public ModelAndView fixSong(@PathVariable String mail, @PathVariable Long day) {
		ModelAndView mav = new ModelAndView("users");
		userService.fixSong(mail, day);
		return mav;
	}
	
	@RequestMapping("/admin/setsongscore/{mail}/{day}/{score}")
	public ModelAndView setSongScore(@PathVariable String mail, @PathVariable Long day, @PathVariable Integer score) {
		ModelAndView mav = new ModelAndView("users");
		userService.fixSongScore(mail, day, score);
		return mav;
	}
	
	@RequestMapping("/admin/setartistscore/{mail}/{day}/{score}")
	public ModelAndView setArtistScore(@PathVariable String mail, @PathVariable Long day, @PathVariable Integer score) {
		ModelAndView mav = new ModelAndView("users");
		userService.fixArtistScore(mail, day, score);
		return mav;
	}
	
	@RequestMapping("/admin/setSongAnswer/{mail}/{day}/{answer}")
	public ModelAndView setSongAnswer(@PathVariable String mail, @PathVariable Long day, @PathVariable String answer) {
		ModelAndView mav = new ModelAndView("users");
		userService.setSongAnswer(mail, day, answer);
		return mav;
	}
	
	@RequestMapping("/admin/setArtistAnswer/{mail}/{day}/{answer}")
	public ModelAndView setArtistAnswer(@PathVariable String mail, @PathVariable Long day, @PathVariable String answer) {
		ModelAndView mav = new ModelAndView("users");
		userService.setArtistAnswer(mail, day, answer);
		return mav;
	}

	@RequestMapping("/admin/fixartist/{mail}/{day}")
	public ModelAndView fixArtist(@PathVariable String mail, @PathVariable Long day) {
		ModelAndView mav = new ModelAndView("users");
		userService.fixArtist(mail, day);
		return mav;
	}

	@RequestMapping(value = "/admin/day/add", method = RequestMethod.GET)
	public ModelAndView addDayGet(@ModelAttribute("day") Day day) {
		ModelAndView mav = new ModelAndView("change_day");
		return mav;
	}

	@RequestMapping(value = "/admin/day/add", method = RequestMethod.POST)
	public ModelAndView addDayPost(@ModelAttribute("day") Day day, BindingResult result) {
		ModelAndView mav = new ModelAndView("change_day");

		if (result.hasErrors()) {
			mav.addObject("error", "There are errors.");
			return mav;
		}

		if (dayService.addDay(day)) {
			mav.addObject("feedback", "La til ny dag! Great success!");
		}
		return mav;
	}

	@RequestMapping(value = "/admin/day/change/{dayNumber}", method = RequestMethod.GET)
	public ModelAndView changeDayGet(@PathVariable Long dayNumber) {
		ModelAndView mav = new ModelAndView("change_day");
		Day day = dayService.getDay(dayNumber);
		mav.addObject("day", day);
		if (day != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
			mav.addObject("revealDate", format.format(day.getRevealDate()));
			mav.addObject("solutionDate", format.format(day.getSolutionDate()));
		}
		return mav;
	}

	@RequestMapping(value = "/admin/day/change", method = RequestMethod.POST)
	public RedirectView changeDayPost(@ModelAttribute("day") Day day, BindingResult result) {
		RedirectView redirectView = new RedirectView("/admin");

		if (result.hasErrors()) {
			redirectView = new RedirectView("/admin?feedback=changefailed");
		}

		dayService.updateDay(day);
		return redirectView;
	}

	@RequestMapping(value = "/admin/day/delete/{dayNumber}")
	public ModelAndView deleteDay(@ModelAttribute("day") Day day, @PathVariable Long dayNumber) {
		ModelAndView mav = new ModelAndView("list_days");
		dayService.deleteDay(dayNumber);
		return mav;
	}

	@RequestMapping(value = "/admin")
	public ModelAndView getAllDays() {
		ModelAndView mav = new ModelAndView("list_days");
		mav.addObject("days", dayService.getDays());
		return mav;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
