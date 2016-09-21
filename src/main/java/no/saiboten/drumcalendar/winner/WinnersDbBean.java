package no.saiboten.drumcalendar.winner;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("winners")
public class WinnersDbBean {

	@Id 
	private String key;

	private Map<String, String> winners;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setWinners(Map<String, String> winners) {
		this.winners = winners;
	}

	public Map<String, String> getWinners() {
		return winners;
	}

	public void addWinner(String day, String winner) {
		this.winners.put(day, winner);
	}

	public WinnersDbBean() {
		winners = new HashMap<String, String>();
	}
}
