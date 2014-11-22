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
	private String answerArtist;

	private boolean correctSong;
	private boolean correctArtist;

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

	public boolean isCorrectArtist() {
		return correctArtist;
	}

	public void setCorrectArtist(boolean correctArtist) {
		this.correctArtist = correctArtist;
	}

	public String getAnswerSong() {
		return answerSong;
	}

	public void setAnswerSong(String answerSong) {
		this.answerSong = answerSong;
	}

	public String getAnswerArtist() {
		return answerArtist;
	}

	public void setAnswerArtist(String answerArtist) {
		this.answerArtist = answerArtist;
	}

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

}
