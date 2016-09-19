package no.saiboten.drumcalendar.user;

import java.util.HashMap;
import java.util.Map;

import no.saiboten.drumcalendar.answer.Answer;
import no.saiboten.drumcalendar.utils.Utils;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("users")
public class CalendarUser implements Comparable<CalendarUser>  {

	@Id ObjectId id;
	
	private int daysCalculated;

	private int rightSong;

	private Map<Long, Answer> answers;

	private String userName;

	public CalendarUser() {
		answers = new HashMap<Long, Answer>();
	}

	public void addAnswer(Long day, Answer answer) {
		this.answers.put(day, answer);
	}

	@Override
	public int compareTo(CalendarUser o) {
		if (o.getTotalScore() > this.getTotalScore()) {
			return 1;
		} else if (o.getTotalScore() < this.getTotalScore()) {
			return -1;
		}
		return 0;
	}

	public void decrementRightSong() {
		this.rightSong--;
	}

	public Map<Long, Answer> getAnswers() {
		return answers;
	}

	public int getDaysCalculated() {
		return daysCalculated;
	}

	public int getRightSong() {
		return rightSong;
	}

	public int getTotalScore() {
		return rightSong;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserNameNotMail() {
		return Utils.removeAtInMail(getUserName());
	}

	public void incrementDaysCalculated() {
		this.daysCalculated++;
	}

	public void incrementRightSong() {
		this.rightSong++;
	}

	public void setAnswers(Map<Long, Answer> answers) {
		this.answers = answers;
	}

	public void setRightSong(int rightSong) {
		this.rightSong = rightSong;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "CalendarUser [daysCalculated=" + daysCalculated + ", rightSong=" + rightSong + ", answers=" + answers + ", userName=" + userName + "]";
	}
}
