package no.saiboten.drumcalendar.user;

import java.util.HashMap;
import java.util.Map;

import no.saiboten.drumcalendar.answer.postgres.AnswerPostgres;
import no.saiboten.drumcalendar.answer.postgres.AnswerRepository;
import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.day.service.DayService;
import no.saiboten.drumcalendar.user.bean.UserResultSingleDay;
import no.saiboten.drumcalendar.user.bean.UserResultSingleUser;
import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserResultService {

	private DayService dayService;
	private CalendarUserService calendarUserService;
	private AnswerRepository answerRepository;

	@Autowired
	public UserResultService(DayService dayService, CalendarUserService calendarUserService, AnswerRepository answerRepository) {
		this.dayService = dayService;
		this.calendarUserService = calendarUserService;
		this.answerRepository = answerRepository;
		
	}
	
	public Map<String,UserResultSingleDay> getUserResults() {
		Map<String,UserResultSingleDay> result = new HashMap<String,UserResultSingleDay>();
		
		for(DayPostgres day : dayService.getDays()) {
			UserResultSingleDay userResultSingleDay = new UserResultSingleDay();
			
			for(CalendarUserPostgres user : calendarUserService.getAllUsers()) {
				
				AnswerPostgres answer = answerRepository.findByUserNameAndDay(user.getUserName(), day.getRevealDateAsString());
				
				if(answer != null && answer.isCorrectSongAnswer()) {
					UserResultSingleUser userResultSingleUser = new UserResultSingleUser();
					userResultSingleUser.setName(user.getUserNameNotMail());
					userResultSingleUser.setTime(answer.getTimeOfCorrectAnswerInMillis());
					userResultSingleDay.addUser(userResultSingleUser);
				}
			}
			result.put(day.getRevealDateAsString(), userResultSingleDay);
		}
		
		return result;
	}
	
}
