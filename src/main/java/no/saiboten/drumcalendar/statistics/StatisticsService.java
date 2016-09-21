package no.saiboten.drumcalendar.statistics;

import java.util.List;

import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;

public interface StatisticsService {
	public Statistics getStatistics();

	public void putStatistics(Statistics stats);

	public void updateStatsForDay(Long day);

	public void deleteStatistics();

	public List<CalendarUserPostgres> getBestUsers();
}
