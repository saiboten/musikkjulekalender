package no.saiboten.drumcalendar.toplist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.saiboten.drumcalendar.answer.postgres.AnswerPostgres;
import no.saiboten.drumcalendar.answer.postgres.AnswerRepository;
import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopListService {
	
	private CalendarUserService calendarUserService;
	private AnswerRepository answerRepository;
	
	

	@Autowired
	public TopListService(CalendarUserService calendarUserService, AnswerRepository answerRepository) {
		this.calendarUserService = calendarUserService;
		this.answerRepository = answerRepository;
	}
	
	public List<TopListModel> getTopList() {
		List<TopListModel> topList = new ArrayList<TopListModel>();
		
		List<CalendarUserPostgres> calendarUsers = calendarUserService.getAllUsers();
		for(CalendarUserPostgres user : calendarUsers) {
			
			Map<Long, Boolean> resultMap = new HashMap<Long,Boolean>();
		
			List<AnswerPostgres> answers = answerRepository.findByUserName(user.getUserName());
			for(AnswerPostgres answer : answers) {
				if(answer.isCorrectSongAnswer()) {					
					resultMap.put(answer.getDay(), true);
				}
			}
			
			int score = 0;
			
			for(boolean val : resultMap.values()) {
				if(val) score++;
			}
			
			topList.add(new TopListModel((user.getNickName() != null && StringUtils.containsNone(user.getNickName(), "@")? user.getNickName() : user.getUserNameNotMail()), score));
		}
		
		Collections.sort(topList,new Comparator<TopListModel>() {

			@Override
			public int compare(TopListModel o1, TopListModel o2) {
				
				if(o1.getScore() > o2.getScore()) {
					return -1;
				}
				else if(o1.getScore()<o2.getScore()) {
					return 1;
				}
				else {
					return 0;

				}
			}
		});
		
		return topList;
	}
}
