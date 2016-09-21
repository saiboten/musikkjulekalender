package no.saiboten.drumcalendar.winner;

import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;

public class Winner {
	private DayPostgres day;
	private CalendarUserPostgres user;

	public CalendarUserPostgres getUser() {
		return user;
	}

	public DayPostgres getDay() {
		return day;
	}

	public void setDay(DayPostgres day) {
		this.day = day;
	}

	public void setUser(CalendarUserPostgres user) {
		this.user = user;
	}
}
