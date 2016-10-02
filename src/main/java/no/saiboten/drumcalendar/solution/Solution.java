package no.saiboten.drumcalendar.solution;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity(name = "solution")
public class Solution extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6734931822844310160L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	private Long day;
	private String solution;

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	@Override
	public String toString() {
		return "Solution [day=" + day + ", solutionSong=" + solution + "]";
	}

}
