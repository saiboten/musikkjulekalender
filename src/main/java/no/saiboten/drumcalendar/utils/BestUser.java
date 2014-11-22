package no.saiboten.drumcalendar.utils;

public class BestUser implements Comparable<BestUser> {
	private String userName;
	private int rightSongs;
	private int rightArtists;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRightSongs() {
		return rightSongs;
	}

	public void setRightSongs(int rightSongs) {
		this.rightSongs = rightSongs;
	}

	public int getRightArtists() {
		return rightArtists;
	}

	public int getRightTotal() {
		return rightArtists + rightSongs;
	}

	public void setRightArtists(int rightArtists) {
		this.rightArtists = rightArtists;
	}

	@Override
	public int compareTo(BestUser o) {
		if (this.getRightTotal() > o.getRightTotal()) {
			return -1;
		} else if (this.getRightTotal() < o.getRightTotal()) {
			return 1;
		}
		return 0;
	}

}
