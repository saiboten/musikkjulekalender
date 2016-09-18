package no.saiboten.drumcalendar.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import no.saiboten.drumcalendar.admin.bean.GenericResponse;
import no.saiboten.drumcalendar.day.DayPostgres;
import no.saiboten.drumcalendar.day.DayService;
import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;
import no.saiboten.drumcalendar.utils.HelperService;
import no.saiboten.drumcalendar.utils.StatisticsService;
import no.saiboten.drumcalendar.winner.WinnerService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public AdminController(CalendarUserService userService,
			StatisticsService statsService, WinnerService winnerService,
			HelperService helperService, DayService dayService,
			LoggedInRequestHolder loggedIn) {
		this.userService = userService;
		this.statsService = statsService;
		this.winnerService = winnerService;
		this.helperService = helperService;
		this.dayService = dayService;
		this.loggedIn = loggedIn;
	}

	@RequestMapping(value = "/admin/resetday/{day}")
	public String resetDay(@PathVariable(value = "day") Long day) {
		helperService.resetDay(day);
		return "answergiven";
	}

	// @RequestMapping("/admin/newwinner/{day}")

	@RequestMapping(value = "/admin/deleteusers")
	public String deleteUsers() {
		userService.deleteAllUsers();
		return "answergiven";
	}

	@RequestMapping(value = "/admin/deletestatistics")
	public String deleteStatistics() {
		statsService.deleteStatistics();
		return "answergiven";
	}

	@RequestMapping(value = "/admin/users")
	public String viewUsers() {
		return "users";
	}

	@RequestMapping(value = "/admin/overview")
	public String adminOverview() {
		// ModelAndView mav = new ModelAndView("overviewadmin");
		// mav.addObject("overview", "active");
		// mav.addObject("now", Calendar.getInstance().getTimeInMillis());
		// mav.addObject("days", dayService.getDays());
		// mav.addObject("loggedIn", loggedIn.isLoggedIn());
		// LOGGER.debug("Is the user logged in?" + loggedIn.isLoggedIn());
		// if (loggedIn.getCalendarUser() != null) {
		// LOGGER.debug("Do we have the calendar user? " +
		// loggedIn.getCalendarUser());
		// mav.addObject("answers", loggedIn.getCalendarUser().getAnswers());
		// }
		//
		// List<CalendarUser> users = userService.getAllUsers();
		// if (users != null) {
		// mav.addObject("numberOfUsers", users.size());
		// mav.addObject("users", users);
		// }
		//
		// mav.addObject("statistics", statsService.getStatistics());
		return "overviewadmin";
	}

	@RequestMapping("/admin/newwinner/{day}")
	public String addWinner(@PathVariable("day") Long day) {
		winnerService.addWinner(day);
		return "users";
	}

	@RequestMapping("/admin/fixsong/{mail}/{day}")
	public String fixSong(@PathVariable String mail, @PathVariable Long day) {
		userService.fixSong(mail, day);
		return "users";
	}

	@RequestMapping("/admin/setsongscore/{mail}/{day}/{score}")
	public String setSongScore(@PathVariable String mail,
			@PathVariable Long day, @PathVariable Integer score) {
		userService.fixSongScore(mail, day, score);
		return "users";
	}

	@RequestMapping("/admin/setSongAnswer/{mail}/{day}/{answer}")
	public String setSongAnswer(@PathVariable String mail,
			@PathVariable Long day, @PathVariable String answer) {
		userService.setSongAnswer(mail, day, answer);
		return "users";
	}

	

	@RequestMapping(value = "/admin/fakelogin/{userid}", method = RequestMethod.GET)
	public RedirectView addDayGet(@PathVariable("userid") String userid) {
		RedirectView redirectView = new RedirectView("/admin");
		loggedIn.setUserName(userid);
		loggedIn.setLoggedIn(true);
		return redirectView;
	}

	@RequestMapping(value = "/admin/day/change/{dayNumber}", method = RequestMethod.GET)
	public ModelAndView changeDayGet(@PathVariable Long dayNumber) {
		ModelAndView mav = new ModelAndView("change_day");
		DayPostgres day = dayService.getDay(dayNumber);
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
	public RedirectView changeDayPost(@ModelAttribute("day") DayPostgres day,
			BindingResult result) {
		RedirectView redirectView = new RedirectView("/admin");

		if (result.hasErrors()) {
			redirectView = new RedirectView("/admin?feedback=changefailed");
		}

		dayService.updateDay(day);
		return redirectView;
	}

	@RequestMapping(value = "/admin/day/delete/{dayNumber}")
	public ModelAndView deleteDay(@ModelAttribute("day") DayPostgres day,
			@PathVariable Long dayNumber) {
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
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

}
