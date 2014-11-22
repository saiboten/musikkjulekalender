package no.saiboten.drumcalendar.bean;

import java.util.HashMap;
import java.util.Map;

public class WinnersDbBean {

	private String key;


	private Map<Long, String> winners;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setWinners(Map<Long, String> winners) {
		this.winners = winners;
	}

	public Map<Long, String> getWinners() {
		return winners;
	}

	public void addWinner(Long day, String winner) {
		this.winners.put(day, winner);
	}

	public WinnersDbBean() {
		winners = new HashMap<Long, String>();
	}
}
