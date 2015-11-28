package no.saiboten.drumcalendar.toplist;

public class TopListModel {
	private String user;
	private int score;
	public TopListModel(String userNameNotMail, int score2) {
		this.user = userNameNotMail;
		this.score = score2;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
