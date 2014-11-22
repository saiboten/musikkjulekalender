package no.saiboten.drumcalendar.utils;

import java.io.Serializable;

public class OneDayStat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5093073779943331676L;

	private int correctArtist;

	private int totalArtist;

	private int correctSong;

	public int getCorrectArtist() {
		return correctArtist;
	}

	public void setCorrectArtist(int correctArtist) {
		this.correctArtist = correctArtist;
	}

	public int getTotalArtist() {
		return totalArtist;
	}

	public void setTotalArtist(int totalArtist) {
		this.totalArtist = totalArtist;
	}

	public int getCorrectSong() {
		return correctSong;
	}

	public void setCorrectSong(int correctSong) {
		this.correctSong = correctSong;
	}

	public int getTotalSong() {
		return totalSong;
	}

	public void setTotalSong(int totalSong) {
		this.totalSong = totalSong;
	}

	private int totalSong;

}
