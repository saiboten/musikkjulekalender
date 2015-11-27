package no.saiboten.drumcalendar.user;

import java.util.HashMap;
import java.util.Map;

import no.saiboten.drumcalendar.utils.Utils;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("users")
public class CalendarUser implements Comparable<CalendarUser>  {

	@Id ObjectId id;
	
	@Override
	public String toString() {
		return "CalendarUser [daysCalculated=" + daysCalculated + ", rightSong=" + rightSong + ", answers=" + answers + ", userName=" + userName + "]";
	}

	private int daysCalculated;

	private int rightSong;

	public void setRightSong(int rightSong) {
		this.rightSong = rightSong;
	}

	private Map<Long, Answer> answers;

	private String userName;

	public int getRightSong() {
		return rightSong;
	}

	public void incrementRightSong() {
		this.rightSong++;
	}

	public void decrementRightSong() {
		this.rightSong--;
	}

	public int getTotalScore() {
		return rightSong;
	}

	public int getDaysCalculated() {
		return daysCalculated;
	}

	public void incrementDaysCalculated() {
		this.daysCalculated++;
	}

	public String getUserNameNotMail() {
		return Utils.removeAtInMail(getUserName());
	}

	public CalendarUser() {
		answers = new HashMap<Long, Answer>();
	}

	public void setAnswers(Map<Long, Answer> answers) {
		this.answers = answers;
	}

	public void addAnswer(Long day, Answer answer) {
		this.answers.put(day, answer);
	}

	public Map<Long, Answer> getAnswers() {
		return answers;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
}
