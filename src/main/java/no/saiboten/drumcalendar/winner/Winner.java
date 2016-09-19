package no.saiboten.drumcalendar.winner;

import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.user.CalendarUser;

public class Winner {
	private DayPostgres day;
	private CalendarUser user;

	public CalendarUser getUser() {
		return user;
	}

	public DayPostgres getDay() {
		return day;
	}

	public void setDay(DayPostgres day) {
		this.day = day;
	}

	public void setUser(CalendarUser user) {
		this.user = user;
	}
}
