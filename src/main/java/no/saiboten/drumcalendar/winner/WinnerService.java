package no.saiboten.drumcalendar.winner;

import java.util.Map;

import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.user.CalendarUser;

public interface WinnerService {
	public Map<DayPostgres, CalendarUser> getWinners();

	public void addWinner(String day);
}
