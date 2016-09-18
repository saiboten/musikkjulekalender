package no.saiboten.drumcalendar.day;

import java.util.List;

public interface DayService {
	public List<DayPostgres> getDays();

	public DayPostgres getDay(Long dayNumber);

	public List<DayPostgres> getSpoilerFreeDays();

	public DayPostgres getToday();

	public boolean addDay(DayPostgres day);

	public boolean updateDay(DayPostgres day);

	public boolean deleteDay(Long dayNumber);
}
