package no.saiboten.drumcalendar.user;

import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;

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
	private String email;
	
	private String nickName;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return email;
	}

	public void setUserName(String userName) {
		this.email = userName;
	}

	private CalendarUserPostgres calendarUser;

	public CalendarUserPostgres getCalendarUser() {
		return calendarUser;
	}

	public void setCalendarUser(CalendarUserPostgres calendarUser) {
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
