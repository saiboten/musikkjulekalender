package no.saiboten.drumcalendar.user;

public class UserStatistics {

	private int correctAnswersArtist = 0;

	private int correctAnswersSong = 0;

	private int totalAnswersArtist = 0;

	private int totalAnswersSong = 0;

	public int getTotalAnswersArtist() {
		return totalAnswersArtist;
	}

	public void setTotalAnswersArtist(int totalAnswersArtist) {
		this.totalAnswersArtist = totalAnswersArtist;
	}

	public int getTotalAnswersSong() {
		return totalAnswersSong;
	}

	public void setTotalAnswersSong(int totalAnswersSong) {
		this.totalAnswersSong = totalAnswersSong;
	}

	public int getCorrectTotal() {
		return correctAnswersArtist + correctAnswersSong;
	}

	public int getCorrectAnswersArtist() {
		return correctAnswersArtist;
	}

	public void setCorrectAnswersArtist(int correctAnswersArtist) {
		this.correctAnswersArtist = correctAnswersArtist;
	}

	public int getCorrectAnswersSong() {
		return correctAnswersSong;
	}

	public void setCorrectAnswersSong(int correctAnswersSong) {
		this.correctAnswersSong = correctAnswersSong;
	}

}
