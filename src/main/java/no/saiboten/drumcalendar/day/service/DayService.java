package no.saiboten.drumcalendar.day.service;

import java.util.List;

import no.saiboten.drumcalendar.day.postgres.DayPostgres;

public interface DayService {
	public List<DayPostgres> getDays();

	public DayPostgres getDay(String dayNumber);

	public List<DayPostgres> getSpoilerFreeDays();

	public DayPostgres getToday();

	public boolean addDay(DayPostgres day);

	public boolean updateDay(DayPostgres day);

	public boolean deleteDay(String dayNumber);
}
