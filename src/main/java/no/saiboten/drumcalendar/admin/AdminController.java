package no.saiboten.drumcalendar.admin;

import java.util.HashMap;
import java.util.Map;

import no.saiboten.drumcalendar.answer.postgres.AnswerRepository;
import no.saiboten.drumcalendar.day.service.DayService;
import no.saiboten.drumcalendar.solution.SolutionRepository;
import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;
import no.saiboten.drumcalendar.winner.WinnerService;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

	private CalendarUserService userService;

	private WinnerService winnerService;

	private DayService dayService;

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	private LoggedInRequestHolder loggedIn;

	private AnswerRepository answerRepository;

	private SolutionRepository solutionRepository;

	@Autowired
	public AdminController(CalendarUserService userService,
			 WinnerService winnerService,
			DayService dayService, LoggedInRequestHolder loggedIn, AnswerRepository answerRepository, SolutionRepository solutionRepository) {
		this.userService = userService;
		this.winnerService = winnerService;
		this.dayService = dayService;
		this.loggedIn = loggedIn;
		this.answerRepository = answerRepository;
		this.solutionRepository = solutionRepository;
	}

	@RequestMapping("/admin/newwinner/{day}")
	public String addWinner(@PathVariable("day") String day) {
		winnerService.addWinner(day);
		return "users";
	}

	@RequestMapping(value = "/admin")
	public String adminPage(Model model) {
		model.addAttribute("loggedin", loggedIn.isLoggedIn());

		return "admin";
	}
	
	@RequestMapping("/admin/alldata")
	public @ResponseBody Map<String,Object> getDays() {
		logger.debug("Getting data");
		


		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		returnMap.put("days", dayService.getDays());
		returnMap.put("user", loggedIn.getCalendarUser());
		returnMap.put("answers",
				answerRepository.findByUserName(loggedIn.getUserName()));
		returnMap.put("isLoggedIn", loggedIn.isLoggedIn());
		returnMap.put("solutions", solutionRepository.findAll());

		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		returnMap.put("date", fmt.print(new DateTime()));
		return returnMap;
	}


}
