package no.saiboten.drumcalendar.service.impl;

import no.saiboten.drumcalendar.bean.WinnersDbBean;
import no.saiboten.drumcalendar.mongodb.MongoDBClientWrapper;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WinnerDao {

	private final Logger LOGGER = Logger.getLogger(this.getClass());
	private MongoDBClientWrapper mongo;

	@Autowired
	public WinnerDao(MongoDBClientWrapper mongo) {
		this.mongo = mongo;
	}
	
	public WinnersDbBean getWinners() {
		LOGGER.debug("Getting winners");
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(mongo.getMongoClient(), "musikkjulekalender2015");
		WinnersDbBean winners = dataStore.find(WinnersDbBean.class).get();
		if(winners == null) {
			WinnersDbBean newWinners = new WinnersDbBean();
			newWinners.setKey("magic");
			saveWinners(newWinners);
			return newWinners;
		}
		else {
			LOGGER.debug("Did we find the winners? " + winners);
			return winners;
		}
		
	}
	
	public boolean saveWinners(WinnersDbBean winners) {
		LOGGER.debug("Saving winners: " +winners);
		winners.setKey("magic");
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(mongo.getMongoClient(), "musikkjulekalender2015");
		dataStore.save(winners);
		return true;
	}

}
