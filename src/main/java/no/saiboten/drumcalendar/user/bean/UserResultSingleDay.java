package no.saiboten.drumcalendar.user.bean;

import java.util.ArrayList;
import java.util.List;

public class UserResultSingleDay {
	List<UserResultSingleUser> users;

	public UserResultSingleDay() {
		users = new ArrayList<UserResultSingleUser>();
	}

	public List<UserResultSingleUser> getUsers() {
		return users;
	}

	public void addUser(UserResultSingleUser user) {
		this.users.add(user);
	}
}
