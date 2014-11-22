package no.saiboten.drumcalendar.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import no.saiboten.drumcalendar.day.Day;
import no.saiboten.drumcalendar.day.DayService;
import no.saiboten.drumcalendar.service.WinnerService;
import no.saiboten.drumcalendar.user.Answer;
import no.saiboten.drumcalendar.user.CalendarUser;
import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.utils.StatisticsController;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CronController {

	private DayService dayService;

	private CalendarUserService userService;

	private StatisticsController statsController;

	private WinnerService winnerService;

	@Autowired
	public CronController(DayService dayService, CalendarUserService userService, StatisticsController statsController,
			WinnerService winnerService) {
		this.dayService = dayService;
		this.userService = userService;
		this.statsController = statsController;
		this.winnerService = winnerService;
	}

	Logger LOGGER = Logger.getLogger(this.getClass().getName());

	@RequestMapping("/cron/updateScores")
	public RedirectView updateScores() {
		List<Day> days = dayService.getDays();
		Date now = new Date();
		for (Day day : days) {
			if (now.after(day.getSolutionDate()) && !day.isProcessed()) {
				day.setProcessed(true);
				updateScoresDay(day.getRevealDateAsInt());
				dayService.updateDay(day);
			}
		}

		return new RedirectView("/");
	}

	@RequestMapping("/cron/updateScores/{day}")
	public RedirectView updateScoresDay(@PathVariable(value = "day") Long day) {
		List<Day> days = dayService.getDays();
		List<CalendarUser> users = userService.getAllUsers();

		for (CalendarUser user : users) {
			Answer answer = user.getAnswers().get(day);
			if (answer != null && answer.getAnswerArtist() != null && answer.getAnswerSong() != null) {
				updateAnswersAndCalculateScores(days, day, user, answer);
			}
		}

		try {
			winnerService.addWinner(day);
		} catch (Exception u) {
			LOGGER.fine("Something went wrong when adding new winner: " + u);
		}

		try {
			statsController.statisticsGivenDay(day);
		} catch (Exception u) {
			LOGGER.fine("Something went wrong when updating statistics: " + u);
		}
		return new RedirectView("/");
	}

	private void updateAnswersAndCalculateScores(List<Day> days, long dayLong, CalendarUser user, Answer answer) {

		for (Day day : days) {
			if (day.getRevealDate().getTime() == dayLong) {
				update(day, user, answer);
				break;
			}
		}
	}

	private void update(Day day, CalendarUser user, Answer answer) {
		if (!answer.isRevealAnswer()) {
			if (containsCaseInsensitive(day.getSolutionsArtist(), answer.getAnswerArtist())) {
				answer.setCorrectArtist(true);
				user.incrementRightArtist();
			}
			if (containsCaseInsensitive(day.getSolutionsSong(), answer.getAnswerSong())) {
				answer.setCorrectSong(true);
				user.incrementRightSong();
			}
			user.incrementDaysCalculated();
			answer.setRevealAnswer(true);
			userService.putUser(user);
		} else {
			LOGGER.fine("Points already calculated for user " + user.getUserName() + " on day " + day.getRevealDate());
		}
	}

	public boolean containsCaseInsensitive(List<String> haystack, String needle) {
		if (needle == null) {
			return false;
		}

		for (String hay : haystack) {
			if (StringUtils.equalsIgnoreCase(hay, needle)) {
				return true;
			}
		}

		return false;
	}

}
