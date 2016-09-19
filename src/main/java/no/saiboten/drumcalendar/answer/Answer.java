package no.saiboten.drumcalendar.answer;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("answer")
public class Answer implements Serializable {
	
	 @Id ObjectId id;
	
	private static final long serialVersionUID = -2227567075127415890L;
	private Long day;
	private String answerSong;
	private boolean correctSong;
	private Long timeOfCorrectAnswerInMillis;

	private boolean revealAnswer;

	public String getAnswerSong() {
		return answerSong;
	}

	public Long getDay() {
		return day;
	}

	public Long getTimeOfCorrectAnswerInMillis() {
		return timeOfCorrectAnswerInMillis;
	}

	public boolean isCorrectSong() {
		return correctSong;
	}

	public boolean isRevealAnswer() {
		return revealAnswer;
	}

	public void setAnswerSong(String answerSong) {
		this.answerSong = answerSong;
	}

	public void setCorrectSong(boolean correctSong) {
		this.correctSong = correctSong;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	public void setRevealAnswer(boolean revealAnswer) {
		this.revealAnswer = revealAnswer;
	}

	public void setTimeOfCorrectAnswerInMillis(Long timeOfCorrectAnswerInMillis) {
		this.timeOfCorrectAnswerInMillis = timeOfCorrectAnswerInMillis;
	}

}
