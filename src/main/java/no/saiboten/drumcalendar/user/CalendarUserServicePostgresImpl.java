package no.saiboten.drumcalendar.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;
import no.saiboten.drumcalendar.user.postgres.CalendarUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalendarUserServicePostgresImpl implements CalendarUserService {

	private CalendarUserRepository calendarUserRepository;

	@Autowired
	public CalendarUserServicePostgresImpl(CalendarUserRepository calendarUserRepository) {
		this.calendarUserRepository = calendarUserRepository;
	
	}
	
	@Override
	public void putUser(CalendarUserPostgres user) {
		calendarUserRepository.save(user);
	}

	@Override
	public CalendarUserPostgres getUser(String uid) {
		return calendarUserRepository.findByUserName(uid);
	}

	@Override
	public void deleteUser(String uid) {
		//TODO impl
	}

	@Override
	public List<CalendarUserPostgres> getAllUsers() {
		List<CalendarUserPostgres> returnUsers = new ArrayList<CalendarUserPostgres>();
		for(CalendarUserPostgres users : calendarUserRepository.findAll() ) {
			returnUsers.add(users);
		}
		return returnUsers;
	}

}
