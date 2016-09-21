package no.saiboten.drumcalendar.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.day.service.DayService;
import no.saiboten.drumcalendar.statistics.StatisticsService;
import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;
import no.saiboten.drumcalendar.winner.WinnerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private DayService dayService;

    Logger logger = LoggerFactory.getLogger(AdminController.class);


	private LoggedInRequestHolder loggedIn;

	@Autowired
	public AdminController(CalendarUserService userService,
			StatisticsService statsService, WinnerService winnerService, DayService dayService,
			LoggedInRequestHolder loggedIn) {
		this.userService = userService;
		this.statsService = statsService;
		this.winnerService = winnerService;
		this.dayService = dayService;
		this.loggedIn = loggedIn;
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

	@RequestMapping("/admin/newwinner/{day}")
	public String addWinner(@PathVariable("day") String day) {
		winnerService.addWinner(day);
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
	public ModelAndView changeDayGet(@PathVariable String dayNumber) {
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
