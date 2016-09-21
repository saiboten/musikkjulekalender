package no.saiboten.drumcalendar.user;

import java.util.List;

import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;

public interface CalendarUserService {

	public void putUser(CalendarUserPostgres user);

	public CalendarUserPostgres getUser(String uid);

	public void deleteUser(String uid);

	public List<CalendarUserPostgres> getAllUsers();
}
