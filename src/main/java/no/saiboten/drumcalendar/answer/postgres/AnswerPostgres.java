package no.saiboten.drumcalendar.answer.postgres;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity(name = "answer")
public class AnswerPostgres extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1281970376170084055L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	private long day;
	private String userName;
	private String guessedSong;
	private boolean correctSongAnswer;
	private long timeOfCorrectAnswerInMillis;
	private boolean answerIsRevealed;

	public long getDay() {
		return day;
	}

	public void setDay(long day) {
		this.day = day;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGuessedSong() {
		return guessedSong;
	}

	public void setGuessedSong(String guessedSong) {
		this.guessedSong = guessedSong;
	}

	public boolean isCorrectSongAnswer() {
		return correctSongAnswer;
	}

	public void setCorrectSongAnswer(boolean correctSongAnswer) {
		this.correctSongAnswer = correctSongAnswer;
	}

	public long getTimeOfCorrectAnswerInMillis() {
		return timeOfCorrectAnswerInMillis;
	}

	public void setTimeOfCorrectAnswerInMillis(long timeOfCorrectAnswerInMillis) {
		this.timeOfCorrectAnswerInMillis = timeOfCorrectAnswerInMillis;
	}

	public boolean isAnswerIsRevealed() {
		return answerIsRevealed;
	}

	public void setAnswerIsRevealed(boolean answerIsRevealed) {
		this.answerIsRevealed = answerIsRevealed;
	}

}
