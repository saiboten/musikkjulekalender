package no.saiboten.drumcalendar.day;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Day implements Comparable<Day> {
	private String image;

	private boolean processed;

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public String getOptionalSolutionVideo() {
		return optionalSolutionVideo;
	}

	public void setOptionalSolutionVideo(String optionalSolutionVideo) {
		this.optionalSolutionVideo = optionalSolutionVideo;
	}

	private String description;
	private List<String> solutionsArtist;
	private List<String> solutionsSong;
	private String optionalSolutionVideo;

	public long getRevealDateAsInt() {
		return revealDateAsInt;
	}
	
	private long revealDateAsInt;

	private String link;

	private Date solutionDate;

	private Date revealDate;

	public Day() {
		solutionsArtist = new ArrayList<String>();
		solutionsSong = new ArrayList<String>();
	}

	public List<String> getSolutionsArtist() {
		return solutionsArtist;
	}

	public void setSolutionsArtist(List<String> solutionsArtist) {
		this.solutionsArtist = solutionsArtist;
	}

	public List<String> getSolutionsSong() {
		return solutionsSong;
	}

	public void setSolutionsSong(List<String> solutionsSong) {
		this.solutionsSong = solutionsSong;
	}

	public String getRealDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(revealDate);
		return String.valueOf(cal.get(Calendar.DATE));
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
		Calendar cal = Calendar.getInstance();
		cal.setTime(revealDate);
		this.revealDateAsInt = cal.getTimeInMillis();
		this.revealDate = revealDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	@Override
	public int compareTo(Day o) {
		if (this.solutionDate.before(o.solutionDate)) {
			return 1;
		} else if (this.solutionDate.after(o.solutionDate)) {
			return -1;
		}
		return 0;
	}
}
