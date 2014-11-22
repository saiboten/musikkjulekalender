package no.saiboten.drumcalendar.controller;

import java.util.logging.Logger;

import no.saiboten.drumcalendar.day.DayService;
import no.saiboten.drumcalendar.service.WinnerService;
import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.utils.StatisticsController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CronControllerTest {
	@Mock
	private DayService dayService;

	@Mock
	private Logger lOGGER;

	@Mock
	private StatisticsController statsController;

	@Mock
	private CalendarUserService userService;

	@Mock
	private WinnerService winnerService;

	private CronController cronController;

	public CronControllerTest() {
		MockitoAnnotations.initMocks(this);
		cronController = new CronController(dayService, userService, statsController, winnerService);
	}

	@Test
	public void testUpdateScores() {
		cronController.updateScores();
	}

}
