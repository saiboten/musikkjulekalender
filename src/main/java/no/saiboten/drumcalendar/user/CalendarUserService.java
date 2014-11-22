package no.saiboten.drumcalendar.user;

import java.util.List;

public interface CalendarUserService {

	public void putUser(CalendarUser user);

	public CalendarUser getUser(String uid);

	public void deleteAllUsers();

	public void deleteUser(String uid);

	public List<CalendarUser> getAllUsers();

	public UserStatistics getUserStatistics(String uid);

	public void deleteAnswersUser(String uid);

	public void fixSong(String mail, Long day);

	public void fixArtist(String mail, Long day);

}
