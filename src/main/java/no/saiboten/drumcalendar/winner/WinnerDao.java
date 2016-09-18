package no.saiboten.drumcalendar.winner;

import no.saiboten.drumcalendar.mongodb.MongoDBClientWrapper;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WinnerDao {

    Logger logger = LoggerFactory.getLogger(WinnerDao.class);

    private MongoDBClientWrapper mongo;

	@Autowired
	public WinnerDao(MongoDBClientWrapper mongo) {
		this.mongo = mongo;
	}
	
	public WinnersDbBean getWinners() {
		logger.debug("Getting winners");
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
			logger.debug("Did we find the winners? " + winners);
			return winners;
		}
		
	}
	
	public boolean saveWinners(WinnersDbBean winners) {
		logger.debug("Saving winners: " +winners);
		winners.setKey("magic");
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(mongo.getMongoClient(), "musikkjulekalender2015");
		dataStore.save(winners);
		return true;
	}

}
