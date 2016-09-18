package no.saiboten.drumcalendar.winner;

import java.util.Map;

import no.saiboten.drumcalendar.day.DayPostgres;
import no.saiboten.drumcalendar.user.CalendarUser;

public interface WinnerService {
	public Map<DayPostgres, CalendarUser> getWinners();

	public void addWinner(Long day);
}
