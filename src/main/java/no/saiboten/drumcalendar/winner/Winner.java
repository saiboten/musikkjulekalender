package no.saiboten.drumcalendar.winner;

import no.saiboten.drumcalendar.day.Day;
import no.saiboten.drumcalendar.user.CalendarUser;

public class Winner {
	private Day day;
	private CalendarUser user;

	public CalendarUser getUser() {
		return user;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public void setUser(CalendarUser user) {
		this.user = user;
	}
}
