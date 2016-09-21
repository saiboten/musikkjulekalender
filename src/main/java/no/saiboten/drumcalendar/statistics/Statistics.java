package no.saiboten.drumcalendar.statistics;

import java.util.HashMap;
import java.util.Map;

import no.saiboten.drumcalendar.utils.OneDayStat;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


public class Statistics {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<Long, Boolean> getActivatedDays() {
		return activatedDays;
	}

	public void setActivatedDays(Map<Long, Boolean> activatedDays) {
		this.activatedDays = activatedDays;
	}

	public Map<Long, OneDayStat> getDayMap() {
		return dayMap;
	}

	public void setDayMap(Map<Long, OneDayStat> dayMap) {
		this.dayMap = dayMap;
	}

	private Map<Long, Boolean> activatedDays;

	private Map<Long, OneDayStat> dayMap;

	public Statistics() {
		activatedDays = new HashMap<Long, Boolean>();
		dayMap = new HashMap<Long, OneDayStat>();

		id = "mysingleton";
	}

}
