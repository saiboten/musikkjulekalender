package no.saiboten.drumcalendar.utils;

import java.util.List;

import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.day.service.DayService;
import no.saiboten.drumcalendar.user.CalendarUser;
import no.saiboten.drumcalendar.user.CalendarUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelperServiceImpl implements HelperService {

	private CalendarUserService userService;

	private DayService dayService;

	@Autowired
	public HelperServiceImpl(CalendarUserService userService, DayService dayService) {
	}

	@Override
	public void resetDay(Long day) {
		for (CalendarUser user : userService.getAllUsers()) {
			userService.deleteAnswersUser(user.getUserName());
		}

		List<DayPostgres> days = dayService.getDays();
	}

}
