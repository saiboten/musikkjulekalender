package no.saiboten.drumcalendar.day.postgres;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity(name="day")
public class DayPostgres extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4936768856326027880L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(unique = true)
	private String revealDateAsString;

	private String image;
	private String description;
	private String optionalSolutionVideo;
	private String link;
	private Date solutionDate;
	private Date revealDate;
	private Integer difficulty;
	private String cooperator;

	private String solutionArtist;
	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public String getCooperator() {
		return cooperator;
	}

	public void setCooperator(String cooperator) {
		this.cooperator = cooperator;
	}

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

	public String getRevealDateAsString() {
		return revealDateAsString;
	}

	public void setRevealDateAsString(String revealDateAsInt) {
		this.revealDateAsString = revealDateAsInt;
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

	public Date getSolutionDate() {
		return solutionDate;
	}

	public void setSolutionDate(Date solutionDate) {
		this.solutionDate = solutionDate;
	}

	public Date getRevealDate() {
		return revealDate;
	}

	public void setRevealDate(Date revealDate) {
		this.revealDate = revealDate;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DayPostgres [id=" + id + ", revealDateAsString="
				+ revealDateAsString + ", image=" + image + ", description="
				+ description + ", optionalSolutionVideo="
				+ optionalSolutionVideo + ", link=" + link + ", solutionDate="
				+ solutionDate + ", revealDate=" + revealDate + ", difficulty="
				+ difficulty + ", cooperator=" + cooperator
				+ ", solutionArtist=" + solutionArtist + ", solutionSong="
				+ solutionSong + "]";
	}

}
