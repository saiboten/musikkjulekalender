package no.saiboten.drumcalendar.day.bean;

import java.util.Date;

/**
 * This is the model for Day used from the client
 * 
 * @author Tobias
 *
 */
public class Day {

	private String image;
	private String description;
	private String optionalSolutionVideo;
	private String link;
	private Date revealDate;
	private String solutionArtist;
	private String solutionSong;

	public String getSolutionArtist() {
		return solutionArtist;
	}

	public void setSolutionArtist(String solutionArtist) {
		this.solutionArtist = solutionArtist;
	}

	public String getSolutionSong() {
		return solutionSong;
	}

	public void setSolutionSong(String solutionSong) {
		this.solutionSong = solutionSong;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOptionalSolutionVideo() {
		return optionalSolutionVideo;
	}

	public void setOptionalSolutionVideo(String optionalSolutionVideo) {
		this.optionalSolutionVideo = optionalSolutionVideo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getRevealDate() {
		return revealDate;
	}

	public void setRevealDate(Date revealDate) {
		this.revealDate = revealDate;
	}
}
