package no.saiboten.drumcalendar.day;

import java.util.List;

public interface DayService {
	public List<Day> getDays();

	public Day getDay(Long dayNumber);

	public List<Day> getSpoilerFreeDays();

	public Day getToday();

	public boolean addDay(Day day);

	public boolean updateDay(Day day);

	public boolean deleteDay(Long dayNumber);
}
