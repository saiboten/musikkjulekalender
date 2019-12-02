package no.saiboten.drumcalendar.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.saiboten.drumcalendar.answer.postgres.AnswerPostgres;
import no.saiboten.drumcalendar.answer.postgres.AnswerRepository;
import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.day.service.DayService;
import no.saiboten.drumcalendar.user.bean.UserResultSingleDay;
import no.saiboten.drumcalendar.user.bean.UserResultSingleUser;
import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;

import org.apache.commons.lang3.StringUtils;
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
		
		DayPostgres day = dayService.getToday();
		
		if(day == null) {
			return null;
		}
		
		UserResultSingleDay userResultSingleDay = new UserResultSingleDay();
		
		for(CalendarUserPostgres user : calendarUserService.getAllUsers()) {
			
			List<AnswerPostgres> answers = answerRepository.findByUserNameAndDay(user.getUserName(), day.getId());
			for(AnswerPostgres answer: answers) {
				if(answer != null && answer.isCorrectSongAnswer()) {
					UserResultSingleUser userResultSingleUser = new UserResultSingleUser();
					userResultSingleUser.setName((user.getNickName() != null && StringUtils.containsNone(user.getNickName(), "@")? user.getNickName() : user.getUserNameNotMail()));
					userResultSingleUser.setTime(answer.getTimeOfCorrectAnswerInMillis());
					userResultSingleDay.addUser(userResultSingleUser);
				}
			}
		}
		result.put(day.getRevealDateAsString(), userResultSingleDay);
	
		return result;
	}
	
}
