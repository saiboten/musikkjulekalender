package no.saiboten.drumcalendar.statistics;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StatisticsController {

	@Autowired
	StatisticsService statsService;

	@RequestMapping("statistics/update")
	public String statistics() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, 0, 0, 0);
		cal.add(Calendar.DATE, -1); // Yesterday! :-)
		return statisticsGivenDay(cal.getTimeInMillis());
	}

	@RequestMapping("statistics/update/{day}")
	public String statisticsGivenDay(@PathVariable("day") Long day) {
		if (day == null) {
			return "error";
		}

		ensureStatsIsCreated();

		statsService.updateStatsForDay(day);

		return "stats";
	}

	private void ensureStatsIsCreated() {
		if (statsService.getStatistics() == null) {
			Statistics newStats = new Statistics();
			statsService.putStatistics(newStats);
		}

	}
}
