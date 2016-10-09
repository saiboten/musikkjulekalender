package no.saiboten.drumcalendar.rest;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import no.saiboten.drumcalendar.answer.postgres.AnswerRepository;
import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.day.service.DayService;
import no.saiboten.drumcalendar.toplist.TopListService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;
import no.saiboten.drumcalendar.user.UserResultService;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
public class RestController {

	private DayService dayService;

	private LoggedInRequestHolder loggedIn;

	private UserResultService userResultService;

	private TopListService topListService;

	Logger logger = LoggerFactory.getLogger(RestController.class);

	private AnswerRepository answerRepository;

	@Autowired
	public RestController(DayService dayService,
			LoggedInRequestHolder loggedIn,
			UserResultService userResultService, TopListService topListService,
			AnswerRepository answerRepository) {
		this.dayService = dayService;
		this.loggedIn = loggedIn;
		this.userResultService = userResultService;
		this.topListService = topListService;
		this.answerRepository = answerRepository;
	}

	@RequestMapping("/alldata")
	public @ResponseBody Map<String,Object> getDays(Principal principal) {
		logger.debug("Principal: " + principal);
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		returnMap.put("days", dayService.getSpoilerFreeDays());
		returnMap.put("user", loggedIn.getCalendarUser());
		returnMap.put("answers",
				answerRepository.findByUserName(loggedIn.getUserName()));
		returnMap.put("isLoggedIn", loggedIn.isLoggedIn());

		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		returnMap.put("date", fmt.print(new DateTime()));
		DayPostgres today = dayService.getToday();
		returnMap.put("today", today != null ? today.getId() : null);

		returnMap.put("userResult", userResultService.getUserResults());
		returnMap.put("topList", topListService.getTopList());

		return returnMap;
	}

	@RequestMapping("/loggedin.json")
	public ModelAndView isLoggedIn() {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJackson2JsonView());
		mav.addObject("isLoggedIn", loggedIn.isLoggedIn());
		return mav;
	}
}
