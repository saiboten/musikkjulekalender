package no.saiboten.drumcalendar.user;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class LoggedInRequestHolder {
	private boolean isLoggedIn;

	/**
	 * Username should really be email to make it unique
	 */
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private CalendarUser calendarUser;

	public CalendarUser getCalendarUser() {
		return calendarUser;
	}

	public void setCalendarUser(CalendarUser calendarUser) {
		this.calendarUser = calendarUser;
	}

	public LoggedInRequestHolder() {
		isLoggedIn = false;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

}
