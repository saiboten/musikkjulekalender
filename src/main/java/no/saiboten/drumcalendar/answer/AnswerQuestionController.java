package no.saiboten.drumcalendar.answer;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import no.saiboten.drumcalendar.answer.postgres.AnswerPostgres;
import no.saiboten.drumcalendar.answer.postgres.AnswerRepository;
import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.day.service.DayService;
import no.saiboten.drumcalendar.solution.Solution;
import no.saiboten.drumcalendar.solution.SolutionRepository;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;
import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AnswerQuestionController {

	Logger LOGGER = LoggerFactory.getLogger(AnswerQuestionController.class);

	private DayService dayService;

	private LoggedInRequestHolder loggedIn;

	private AnswerRepository answerRepository;

	private SolutionRepository solutionRepository;

	@Autowired
	public AnswerQuestionController(DayService dayService,
			LoggedInRequestHolder loggedIn, AnswerRepository answerRepository,
			SolutionRepository solutionRepository) {
		this.dayService = dayService;
		this.loggedIn = loggedIn;
		this.answerRepository = answerRepository;
		this.solutionRepository = solutionRepository;
	}

	@RequestMapping(path="/api/answer", method=RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> answerQuestion(@RequestParam(value = "guess") String song) {
		Map<String, Object> answerMap = new HashMap<String, Object>();
		if (song == null || song.equals("")) {
			answerMap.put("feedback", "Sang var tom. Vennligst prøv igjen..");
			return answerMap;
		}

		song = song.trim();

		Pattern pattern = Pattern.compile("(\\w|\\s|[0-9]|\\?|\\&|ø|æ|å|ä|ö)+");
		Matcher matchSong = pattern.matcher(song);
		if (!matchSong.matches()) {
			answerMap
					.put("feedback",
							"Forslaget inneholdt ulovlige tegn. Kun bokstaver og tall er tillatt. Dersom svaret ditt inneholder spesielle tegn, kan du fjerne disse, vi har i så fall gjort det samme i fasiten");
			return answerMap;
		}

		CalendarUserPostgres user = loggedIn.getCalendarUser();
		DayPostgres today = dayService.getToday();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today.getRevealDate());

		List<AnswerPostgres> existingAnswers = answerRepository.findByUserNameAndDay(user.getUserName(), today.getId());

		LOGGER.debug("Date is: " + cal.getTimeInMillis() + ". User is: " + user);
		if (user != null && !answerExists(existingAnswers, song)) {
			AnswerPostgres answerPostgres = storeAnswer(song, user, today);
			if (answerPostgres.isCorrectSongAnswer()) {
				answerMap.put("feedback", "Riktig! Svaret var: " + song
						+ ". Godt jobbet!");
				answerMap.put("correct", true);
			} else {
				answerMap.put("feedback",
						"Beklager, svaret var feil! Du tippet: " + song);
				answerMap.put("correct", false);

			}

		} else {
			answerMap.put("feedback", "Dette svaret har du prøvd tidligere!");
			answerMap.put("correct", false);
		}

		return answerMap;
	}

	private boolean answerExists(List<AnswerPostgres> existingAnswers,
			String song) {
		boolean exists = false;
		
		for (AnswerPostgres answerPostgres : existingAnswers) {
			if(song.equals(answerPostgres.getGuessedSong())) {
				exists = true;
			}
		}
		
		return exists;
	}

	private AnswerPostgres storeAnswer(String song, CalendarUserPostgres user,
			DayPostgres today) {
		AnswerPostgres answerPostgres = new AnswerPostgres();

		List<Solution> solutions = solutionRepository.findByDay(today.getId());

		LOGGER.debug("Solutions: " + solutions);
		answerPostgres.setDay(today.getId());
		answerPostgres.setGuessedSong(song);
		answerPostgres.setUserName(user.getUserName());

		for (Solution songSolution : solutions) {
			if (songSolution.getSolution().toLowerCase()
					.equals(song.toLowerCase())) {
				answerPostgres.setCorrectSongAnswer(true);
				answerPostgres.setTimeOfCorrectAnswerInMillis(new Date()
						.getTime());
			}
		}

		answerRepository.save(answerPostgres);
		return answerPostgres;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/api/admin/addsolution/{day}/{solution}")
	public @ResponseBody
	Map<String, Object> addSolution(@PathVariable Long day,
			@PathVariable(value = "solution") String solutionString) {
		Solution solution = new Solution();
		solution.setDay(day);
		solution.setSolution(solutionString);
		solutionRepository.save(solution);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("success", true);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/api/admin/deletesolution/{day}/{solution}")
	public @ResponseBody
	Map<String, Object> deleteSolution(@PathVariable Long day,
			@PathVariable(value = "solution") String solutionString) {

		solutionRepository.deleteByDayAndSolution(day, solutionString);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("success", true);
		return response;
	}

}
