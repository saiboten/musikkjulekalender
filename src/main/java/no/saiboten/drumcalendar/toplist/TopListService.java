package no.saiboten.drumcalendar.toplist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import no.saiboten.drumcalendar.user.Answer;
import no.saiboten.drumcalendar.user.CalendarUser;
import no.saiboten.drumcalendar.user.CalendarUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopListService {
	
	private CalendarUserService calendarUserService;

	@Autowired
	public TopListService(CalendarUserService calendarUserService) {
		this.calendarUserService = calendarUserService;
	}
	
	public List<TopListModel> getTopList() {
		List<TopListModel> topList = new ArrayList<TopListModel>();
		
		
		List<CalendarUser> calendarUsers = calendarUserService.getAllUsers();
		for(CalendarUser user : calendarUsers) {
			int score = 0;
			for(Answer answer : user.getAnswers().values()) {
				if(answer.isCorrectSong()) {
					score++;
				}
			}
			topList.add(new TopListModel(user.getUserNameNotMail(), score));
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
