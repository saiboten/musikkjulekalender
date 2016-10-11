package no.saiboten.drumcalendar.user.postgres;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="participant")
public class CalendarUserPostgres {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	private String userName;
	private String nickName;
	private int daysCalculated;
	private int daysGuessedRight;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getDaysCalculated() {
		return daysCalculated;
	}
	public void setDaysCalculated(int daysCalculated) {
		this.daysCalculated = daysCalculated;
	}
	public int getDaysGuessedRight() {
		return daysGuessedRight;
	}
	public void setDaysGuessedRight(int daysGuessedRight) {
		this.daysGuessedRight = daysGuessedRight;
	}
	public String getUserNameNotMail() {
		return userName.split("@")[0];
	}
	
	
}
