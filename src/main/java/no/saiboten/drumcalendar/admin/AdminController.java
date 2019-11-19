package no.saiboten.drumcalendar.admin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import no.saiboten.drumcalendar.answer.postgres.AnswerRepository;
import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.day.service.DayService;
import no.saiboten.drumcalendar.solution.SolutionRepository;
import no.saiboten.drumcalendar.storage.StorageService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

	private CalendarUserService userService;

	private WinnerService winnerService;

	private DayService dayService;

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	private LoggedInRequestHolder loggedIn;

	private AnswerRepository answerRepository;

	private SolutionRepository solutionRepository;

	private StorageService storageService;

	@Autowired
	public AdminController(CalendarUserService userService,
			WinnerService winnerService, DayService dayService,
			LoggedInRequestHolder loggedIn, AnswerRepository answerRepository,
			SolutionRepository solutionRepository, StorageService storageService) {
		this.userService = userService;
		this.winnerService = winnerService;
		this.dayService = dayService;
		this.loggedIn = loggedIn;
		this.answerRepository = answerRepository;
		this.solutionRepository = solutionRepository;
		this.storageService = storageService;
	}

	@RequestMapping("/api/admin/newwinner/{day}")
	public String addWinner(@PathVariable("day") String day) {
		winnerService.addWinner(day);
		return "users";
	}

	@RequestMapping(value = "/admin")
	public String adminPage(Model model) {
		model.addAttribute("loggedin", loggedIn.isLoggedIn());

		return "admin";
	}

	@RequestMapping("/api/admin/alldata")
	public @ResponseBody Map<String, Object> getDays() {
		logger.debug("Getting data");

		Map<String, Object> returnMap = new HashMap<String, Object>();

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

	@PostMapping("/api/admin/upload/{dayIdAsString}")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			@PathVariable String dayIdAsString,
			RedirectAttributes redirectAttributes) {
		logger.debug("File upload began! Day id: " + dayIdAsString);
		DayPostgres day = dayService.getDay(dayIdAsString);
		String songName = UUID.randomUUID().toString() + ".mp3";
		day.setLink("/songs/" + songName);

		storageService.store(file, songName);
		dayService.updateDay(day);
		return "redirect:/";
	}

}
