package no.saiboten.drumcalendar.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import no.saiboten.drumcalendar.day.DayPostgres;
import no.saiboten.drumcalendar.day.DayService;
import no.saiboten.drumcalendar.user.Answer;
import no.saiboten.drumcalendar.user.CalendarUser;
import no.saiboten.drumcalendar.user.CalendarUserService;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
 
public class StatisticsServiceImplTest {

	StatisticsServiceImpl statService;

	@Mock
	CalendarUserService userService;

	@Mock
	DayService dayService;

	public StatisticsServiceImplTest() {
		MockitoAnnotations.initMocks(this);
//		statService = new StatisticsServiceImpl(userService, dayService);
	}

//	@Test
	public void testGetTopThree() {

		DayPostgres day1 = new DayPostgres();
		// day1.setSolutionArtist("rightArtist");
		// day1.setSolutionSong("rightSong");

		CalendarUser user1 = new CalendarUser();
		Map<Long, Answer> answersUser1 = new HashMap<Long, Answer>();
		Answer answerDay1User1 = new Answer();
		answerDay1User1.setAnswerSong("rightSong");
		user1.setAnswers(answersUser1);
		answersUser1.put(new Long(1), answerDay1User1);

		CalendarUser user2 = new CalendarUser();
		Map<Long, Answer> answersUser2 = new HashMap<Long, Answer>();
		Answer answerDay1User2 = new Answer();
		answerDay1User1.setAnswerSong("wrongSong");
		user2.setAnswers(answersUser2);
		user2.setAnswers(answersUser1);
		answersUser2.put(new Long(1), answerDay1User2);

		Mockito.when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));
		Mockito.when(dayService.getDays()).thenReturn(Arrays.asList(day1));

		// List<BestUser> bestUsers = statService.getBestUser();

	}
}
