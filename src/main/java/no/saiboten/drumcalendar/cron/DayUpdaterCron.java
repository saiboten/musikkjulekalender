package no.saiboten.drumcalendar.cron;

import java.util.Date;
import java.util.List;

import no.saiboten.drumcalendar.day.Day;
import no.saiboten.drumcalendar.day.DayService;
import no.saiboten.drumcalendar.service.WinnerService;
import no.saiboten.drumcalendar.user.Answer;
import no.saiboten.drumcalendar.user.CalendarUser;
import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.utils.StatisticsController;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DayUpdaterCron {
	
	private DayService dayService;
	private CalendarUserService userService;
	private StatisticsController statsController;
	private WinnerService winnerService;
	
	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	public DayUpdaterCron(DayService dayService, CalendarUserService userService, StatisticsController statsController,
			WinnerService winnerService) {
		this.dayService = dayService;
		this.userService = userService;
		this.statsController = statsController;
		this.winnerService = winnerService;
	}
	
	@Scheduled(cron="5 0 0 * * *")
	public void updateScores() {
		LOGGER.info("Updating scores!");
		List<Day> days = dayService.getDays();
		Date now = new Date();
		for (Day day : days) {
			if (now.after(day.getSolutionDate()) && !day.isProcessed()) {
				day.setProcessed(true);
				updateScoresDay(day.getRevealDateAsInt());
				dayService.updateDay(day);
			}
		}
	}
	
	public void updateScoresDay(Long day) {
		List<CalendarUser> users = userService.getAllUsers();

		for (CalendarUser user : users) {
			Answer answer = user.getAnswers().get(day);
			if (answer != null && answer.getAnswerSong() != null) {
				updateAnswersAndCalculateScores(day, user, answer);
			}
		}

		try {
			winnerService.addWinner(day);
		} catch (Exception u) {
			LOGGER.debug("Something went wrong when adding new winner: " + u);
		}

		try {
			statsController.statisticsGivenDay(day);
		} catch (Exception u) {
			LOGGER.debug("Something went wrong when updating statistics: " + u);
		}
	}
	
	public void updateAnswersAndCalculateScores(long dayLong, CalendarUser user, Answer answer) {
		List<Day> days = dayService.getDays();

		for (Day day : days) {
			if (day.getRevealDate().getTime() == dayLong) {
				update(day, user, answer);
				break;
			}
		}
	}

	private void update(Day day, CalendarUser user, Answer answer) {
		if (!answer.isRevealAnswer()) {
			if (containsCaseInsensitive(day.getSolutionsSong(), answer.getAnswerSong())) {
				answer.setCorrectSong(true);
				user.incrementRightSong();
			}
			user.incrementDaysCalculated();
			answer.setRevealAnswer(true);
			userService.putUser(user);
		} else {
			LOGGER.debug("Points already calculated for user " + user.getUserName() + " on day " + day.getRevealDate());
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
