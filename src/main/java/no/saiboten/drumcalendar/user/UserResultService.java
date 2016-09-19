package no.saiboten.drumcalendar.user;

import java.util.HashMap;
import java.util.Map;

import no.saiboten.drumcalendar.answer.Answer;
import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.day.service.DayService;
import no.saiboten.drumcalendar.user.bean.UserResultSingleDay;
import no.saiboten.drumcalendar.user.bean.UserResultSingleUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserResultService {

	private DayService dayService;
	private CalendarUserService calendarUserService;

	@Autowired
	public UserResultService(DayService dayService, CalendarUserService calendarUserService) {
		this.dayService = dayService;
		this.calendarUserService = calendarUserService;
		
	}
	
	public Map<Long,UserResultSingleDay> getUserResults() {
		Map<Long,UserResultSingleDay> result = new HashMap<Long,UserResultSingleDay>();
		
		for(DayPostgres day : dayService.getDays()) {
			UserResultSingleDay userResultSingleDay = new UserResultSingleDay();
			
			for(CalendarUser user : calendarUserService.getAllUsers()) {
				Answer answer = user.getAnswers().get(day.getRevealDateAsInt());
				if(answer != null && answer.isCorrectSong()) {
					UserResultSingleUser userResultSingleUser = new UserResultSingleUser();
					userResultSingleUser.setName(user.getUserNameNotMail());
					userResultSingleUser.setTime(answer.getTimeOfCorrectAnswerInMillis());
					userResultSingleDay.addUser(userResultSingleUser);
				}
			}
			result.put(day.getRevealDateAsInt(), userResultSingleDay);
		}
		
		return result;
	}
	
}
