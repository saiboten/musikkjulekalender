package no.saiboten.drumcalendar.user;

import java.io.Serializable;

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

	private boolean revealAnswer;

	public boolean isRevealAnswer() {
		return revealAnswer;
	}

	public void setRevealAnswer(boolean revealAnswer) {
		this.revealAnswer = revealAnswer;
	}

	public boolean isCorrectSong() {
		return correctSong;
	}

	public void setCorrectSong(boolean correctSong) {
		this.correctSong = correctSong;
	}

	public String getAnswerSong() {
		return answerSong;
	}

	public void setAnswerSong(String answerSong) {
		this.answerSong = answerSong;
	}

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

}
