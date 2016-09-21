package no.saiboten.drumcalendar.winner;

import java.util.Map;

import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;

public interface WinnerService {
	public Map<DayPostgres, CalendarUserPostgres> getWinners();

	public void addWinner(String day);
}
