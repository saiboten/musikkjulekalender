package no.saiboten.drumcalendar.service;

import java.util.Map;

import no.saiboten.drumcalendar.day.Day;
import no.saiboten.drumcalendar.user.CalendarUser;

public interface WinnerService {
	public Map<Day, CalendarUser> getWinners();

	public void addWinner(Long day);
}
