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
	public AnswerQuestionController(DayService dayService, LoggedInRequestHolder loggedIn, AnswerRepository answerRepository, SolutionRepository solutionRepository) {
		this.dayService = dayService;
		this.loggedIn = loggedIn;
		this.answerRepository = answerRepository;
		this.solutionRepository = solutionRepository;
	}

	@RequestMapping("/answer")
	public @ResponseBody Map<String,Object> answerQuestion(@RequestParam(value = "song") String song) {
		Map<String,Object> answerMap = new HashMap<String,Object>();
		if (song == null || song.equals("")) {
			answerMap.put("feedback", "Sang var tom. Vennligst prøv igjen..");
			return answerMap;
		}

		song = song.trim();

		Pattern pattern = Pattern.compile("(\\w|\\s|[0-9]|\\?|\\&|ø|æ|å)+");
		Matcher matchSong = pattern.matcher(song);
		if (!matchSong.matches()) {
			answerMap.put("feedback",
					"Forslaget inneholdt ulovlige tegn. Kun bokstaver og tall er tillatt. Dersom svaret ditt inneholder spesielle tegn, kan du fjerne disse, vi har i så fall gjort det samme i fasiten");
			return answerMap;
		}

		CalendarUserPostgres user = loggedIn.getCalendarUser();
		DayPostgres today = dayService.getToday();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today.getRevealDate());

		LOGGER.debug("Date is: " + cal.getTimeInMillis() + ". User is: " + user);
		if (user != null) {
			AnswerPostgres answerPostgres = new AnswerPostgres();
			
			List<Solution> solutions = solutionRepository.findByDay(today.getRevealDateAsString());
			
			LOGGER.debug("Solutions: " + solutions);
			answerPostgres.setDay(today.getRevealDateAsString());
			answerPostgres.setGuessedSong(song);
			answerPostgres.setUserName(user.getUserName());
			
			for(Solution songSolution : solutions) {
				if(songSolution.getSolution().toLowerCase().equals(song.toLowerCase())) {
					answerPostgres.setCorrectSongAnswer(true);
					answerPostgres.setTimeOfCorrectAnswerInMillis(new Date().getTime());
				}
			}
			
			answerRepository.save(answerPostgres);
			
			if(answerPostgres.isCorrectSongAnswer()) {
				answerMap.put("feedback", "Riktig! Svaret var: " + song + ". Godt jobbet!");
				answerMap.put("correct", true);
			}
			else {
				answerMap.put("feedback", "Beklager, svaret var feil! Du tippet: " + song);
				answerMap.put("correct", false);

			}
			
		} else {
			answerMap.put("feedback", "Noe gikk galt. Er du innlogget?");
			answerMap.put("correct", false);
		}

		return answerMap;
	}
	
	@RequestMapping("/admin/addsolution/{day}/{solution}")
	public @ResponseBody Map<String,Object> addSolution(@PathVariable String day, @PathVariable(value="solution") String solutionString) {
		Solution solution = new Solution();
		solution.setDay(day);
		solution.setSolution(solutionString);
		solutionRepository.save(solution);
		
		Map<String,Object> response = new HashMap<String,Object>();
		response.put("success", true);
		return response;
	}
	
}
