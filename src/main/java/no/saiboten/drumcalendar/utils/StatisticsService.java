package no.saiboten.drumcalendar.utils;

import java.util.List;

import no.saiboten.drumcalendar.user.CalendarUser;

public interface StatisticsService {
	public Statistics getStatistics();

	public void putStatistics(Statistics stats);

	public void updateStatsForDay(Long day);

	public void deleteStatistics();

	public List<CalendarUser> getBestUsers();
}
