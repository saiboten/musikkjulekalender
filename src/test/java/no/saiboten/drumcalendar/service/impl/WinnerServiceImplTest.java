package no.saiboten.drumcalendar.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.saiboten.drumcalendar.day.DayService;
import no.saiboten.drumcalendar.user.Answer;
import no.saiboten.drumcalendar.user.CalendarUser;
import no.saiboten.drumcalendar.user.CalendarUserService;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class WinnerServiceImplTest {
	WinnerServiceImpl winnerServiceImpl;

	@Mock
	CalendarUserService calendarUserService;

	@Mock
	WinnerDao winnerDao;

	@Mock
	DayService dayService;

	CalendarUser user1 = new CalendarUser();
	CalendarUser user2 = new CalendarUser();

	List<CalendarUser> calendarUsers = new ArrayList<CalendarUser>();

	public WinnerServiceImplTest() {
		MockitoAnnotations.initMocks(this);
		winnerServiceImpl = new WinnerServiceImpl(calendarUserService, winnerDao, dayService);

		calendarUsers.add(user1);
		user1.setUserName("myuser@gmail.com");
		calendarUsers.add(user2);
		user2.setUserName("user2@gmail.com");
	}

	@Test
	public void test_no_winners() {

		Mockito.when(calendarUserService.getAllUsers()).thenReturn(calendarUsers);

		String winner = winnerServiceImpl.findWinner(new Long(1));
		assertNull(winner);
	}

	@Test
	public void test_when_only_one_winner_select_that_one() {

		Map<Long, Answer> answersUser1 = new HashMap<Long, Answer>();
		Answer answerUser1 = new Answer();
		answerUser1.setCorrectSong(true);

		answersUser1.put(new Long(1), answerUser1);

		user1.setAnswers(answersUser1);
		Mockito.when(calendarUserService.getAllUsers()).thenReturn(calendarUsers);

		String winner = winnerServiceImpl.findWinner(new Long(1));
		assertEquals("myuser@gmail.com", winner);
	}

	@Test
	public void test_one_winner_among_two_answers() {

		Map<Long, Answer> answersUser1 = new HashMap<Long, Answer>();
		Answer answerUser1 = new Answer();
		answerUser1.setCorrectSong(true);
		answersUser1.put(new Long(1), answerUser1);

		Map<Long, Answer> answersUser2 = new HashMap<Long, Answer>();
		Answer answerUser2 = new Answer();
		answerUser2.setCorrectSong(false);
		answersUser2.put(new Long(1), answerUser2);

		user1.setAnswers(answersUser1);
		user2.setAnswers(answersUser2);
		Mockito.when(calendarUserService.getAllUsers()).thenReturn(calendarUsers);
		assertEquals("myuser@gmail.com", winnerServiceImpl.findWinner(new Long(1)));
	}
}
