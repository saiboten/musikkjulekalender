package no.saiboten.drumcalendar.winner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import no.saiboten.drumcalendar.answer.postgres.AnswerPostgres;
import no.saiboten.drumcalendar.answer.postgres.AnswerRepository;
import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.day.service.DayService;
import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;
import no.saiboten.drumcalendar.winner.postgres.WinnerPostgres;
import no.saiboten.drumcalendar.winner.postgres.WinnerRepository;

import org.joda.time.Days;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WinnerServiceImpl implements WinnerService {
	
    Logger logger = LoggerFactory.getLogger(WinnerServiceImpl.class);

	CalendarUserService userService;

	DayService dayService;

	private WinnerRepository winnerRepository;

	private AnswerRepository answerRepository;


	@Autowired
	public WinnerServiceImpl(CalendarUserService userService, DayService dayService, WinnerRepository winnerRepository, AnswerRepository answerRepository) {
		this.userService = userService;
		this.dayService = dayService;
		this.winnerRepository = winnerRepository;
		this.answerRepository = answerRepository;
	}

	@Override
	public Map<DayPostgres, CalendarUserPostgres> getWinners() {
		Map<DayPostgres, CalendarUserPostgres> winnerMap = new HashMap<DayPostgres, CalendarUserPostgres>();
		
		for(WinnerPostgres winner : winnerRepository.findAll()) {
			winnerMap.put(dayService.getDay(winner.getDay()), userService.getUser(winner.getWinner()));
		}
	
		return winnerMap;
	}

	@Override
	public void addWinner(String day) {

		String winner = findWinner(day);
		if (winner == null) {
			logger.debug("No winner found...");
			return;
		}
		
	
		logger.debug("Adding winner for day " + day + ": " + winner);
		WinnerPostgres winnerPostgres = new WinnerPostgres();
		winnerPostgres.setDay(day);
		winnerPostgres.setWinner(winner);
		winnerRepository.save(winnerPostgres);
	}

	protected String findWinner(String day) {
		List<CalendarUserPostgres> users = userService.getAllUsers();
		List<CalendarUserPostgres> possibleWinners = new ArrayList<CalendarUserPostgres>();
		for (CalendarUserPostgres user : users) {
			
			AnswerPostgres answer = answerRepository.findByUserNameAndDay(user.getUserName(), day);
			
			if(answer != null) {
				logger.debug("Correct song? " + answer.isCorrectSongAnswer());
			}
			
			if (answer != null && answer.isCorrectSongAnswer()) {
				possibleWinners.add(user);
			}
		}

		Random random = new Random();
		if (possibleWinners.size() == 0) {
			logger.debug("No winners found this day");
			return null;
		}
		int winner = random.nextInt(possibleWinners.size());
		CalendarUserPostgres winnerUser = possibleWinners.get(winner);
		if (winnerUser != null) {
			return winnerUser.getUserName();
		}

		return null;
	}
}
