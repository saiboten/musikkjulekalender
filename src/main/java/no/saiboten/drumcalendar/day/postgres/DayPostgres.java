package no.saiboten.drumcalendar.day.postgres;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
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
	private long revealDateAsInt;

	private String image;
	private String description;
	private String optionalSolutionVideo;
	private String link;
	private Date solutionDate;
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

	public long getRevealDateAsInt() {
		return revealDateAsInt;
	}

	public void setRevealDateAsInt(long revealDateAsInt) {
		this.revealDateAsInt = revealDateAsInt;
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
}
