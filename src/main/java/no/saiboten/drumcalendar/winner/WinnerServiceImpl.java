package no.saiboten.drumcalendar.winner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import no.saiboten.drumcalendar.day.DayPostgres;
import no.saiboten.drumcalendar.day.DayService;
import no.saiboten.drumcalendar.user.Answer;
import no.saiboten.drumcalendar.user.CalendarUser;
import no.saiboten.drumcalendar.user.CalendarUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WinnerServiceImpl implements WinnerService {
	
    Logger logger = LoggerFactory.getLogger(WinnerServiceImpl.class);

	CalendarUserService userService;

	DayService dayService;

	WinnerDao winnerDao;

	@Autowired
	public WinnerServiceImpl(CalendarUserService userService, WinnerDao winnerDao, DayService dayService) {
		this.userService = userService;
		this.winnerDao = winnerDao;
		this.dayService = dayService;
	}

	@Override
	public Map<DayPostgres, CalendarUser> getWinners() {
		Map<DayPostgres, CalendarUser> winnerMap = new HashMap<DayPostgres, CalendarUser>();
		WinnersDbBean winnersDbBean = winnerDao.getWinners();

		Map<Long, String> winnersDbMap = winnersDbBean.getWinners();
		for (Long dayId : winnersDbMap.keySet()) {
			winnerMap.put(dayService.getDay(dayId), userService.getUser(winnersDbMap.get(dayId)));
		}
		return winnerMap;
	}

	@Override
	public void addWinner(Long day) {

		String winner = findWinner(day);
		if (winner == null) {
			logger.debug("No winner found...");
			return;
		}

		WinnersDbBean winnersDbBean = winnerDao.getWinners();
		winnersDbBean.getWinners().put(day, winner);

		logger.debug("Adding winner for day " + day + ": " + winner);
		winnerDao.saveWinners(winnersDbBean);
	}

	protected String findWinner(Long day) {
		List<CalendarUser> users = userService.getAllUsers();
		List<CalendarUser> possibleWinners = new ArrayList<CalendarUser>();
		for (CalendarUser user : users) {
			Answer answer = user.getAnswers().get(day);
			if(answer != null) {
				logger.debug("Correct song? " + answer.isCorrectSong());
			}
			
			if (answer != null && answer.isCorrectSong()) {
				possibleWinners.add(user);
			}
		}

		Random random = new Random();
		if (possibleWinners.size() == 0) {
			logger.debug("No winners found this day");
			return null;
		}
		int winner = random.nextInt(possibleWinners.size());
		CalendarUser winnerUser = possibleWinners.get(winner);
		if (winnerUser != null) {
			return winnerUser.getUserName();
		}

		return null;
	}
}
