package no.saiboten.drumcalendar.winner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.saiboten.drumcalendar.answer.postgres.AnswerPostgres;
import no.saiboten.drumcalendar.answer.postgres.AnswerRepository;
import no.saiboten.drumcalendar.day.service.DayService;
import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;
import no.saiboten.drumcalendar.winner.postgres.WinnerRepository;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class WinnerServiceImplTest {
	WinnerServiceImpl winnerServiceImpl;

	@Mock
	CalendarUserService calendarUserService;

	@Mock
	WinnerRepository winnerDao;

	@Mock
	DayService dayService;
	
	@Mock
	AnswerRepository answerRepository;

	CalendarUserPostgres user1 = new CalendarUserPostgres();
	CalendarUserPostgres user2 = new CalendarUserPostgres();

	List<CalendarUserPostgres> calendarUsers = new ArrayList<CalendarUserPostgres>();

	public WinnerServiceImplTest() {
		MockitoAnnotations.initMocks(this);
		winnerServiceImpl = new WinnerServiceImpl(calendarUserService, dayService, winnerDao,answerRepository);

		calendarUsers.add(user1);
		user1.setUserName("myuser@gmail.com");
		calendarUsers.add(user2);
		user2.setUserName("user2@gmail.com");
	}

	@Test
	public void test_no_winners() {

		Mockito.when(calendarUserService.getAllUsers()).thenReturn(calendarUsers);

		String winner = winnerServiceImpl.findWinner("2015-01-01");
		assertNull(winner);
	}

	@Test
	public void test_when_only_one_winner_select_that_one() {

		Map<Long, AnswerPostgres> answersUser1 = new HashMap<Long, AnswerPostgres>();
		AnswerPostgres answerUser1 = new AnswerPostgres();
		answerUser1.setCorrectSongAnswer(true);

		answersUser1.put(new Long(1), answerUser1);

		Mockito.when(calendarUserService.getAllUsers()).thenReturn(calendarUsers);

		String winner = winnerServiceImpl.findWinner("2015-01-01");
		assertEquals("myuser@gmail.com", winner);
	}

	@Test
	public void test_one_winner_among_two_answers() {

		Map<Long, AnswerPostgres> answersUser1 = new HashMap<Long, AnswerPostgres>();
		AnswerPostgres answerUser1 = new AnswerPostgres();
		answerUser1.setCorrectSongAnswer(true);
		answersUser1.put(new Long(1), answerUser1);

		Map<Long, AnswerPostgres> answersUser2 = new HashMap<Long, AnswerPostgres>();
		AnswerPostgres answerUser2 = new AnswerPostgres();
		answerUser2.setCorrectSongAnswer(false);
		answersUser2.put(new Long(1), answerUser2);

		Mockito.when(calendarUserService.getAllUsers()).thenReturn(calendarUsers);
		assertEquals("myuser@gmail.com", winnerServiceImpl.findWinner("2015-01-01"));
	}
}
