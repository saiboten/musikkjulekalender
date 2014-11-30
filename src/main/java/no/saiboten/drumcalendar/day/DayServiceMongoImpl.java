package no.saiboten.drumcalendar.day;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import no.saiboten.drumcalendar.mongodb.MongoDBClientWrapper;
import no.saiboten.drumcalendar.utils.Utils;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DayServiceMongoImpl implements DayService {
	private MongoDBClientWrapper mongo;
	
	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	public DayServiceMongoImpl(MongoDBClientWrapper mongo) {
		this.mongo = mongo;
	}

	@Override
	public List<Day> getDays() {
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(mongo.getMongoClient(), "musikkjulekalender");
		List<Day> allDays = dataStore.find(Day.class).asList();
		LOGGER.debug("Did we find all the users? " + allDays);
		Collections.sort(allDays);
		Collections.reverse(allDays);
		return allDays;
	}

	@Override
	public Day getDay(Long dayNumber) {
		LOGGER.debug("Getting day: " + dayNumber);
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(mongo.getMongoClient(), "musikkjulekalender");
		Day theCorrectDay = dataStore.find(Day.class).field("revealDateAsInt").equal(dayNumber).get();
		LOGGER.debug("Did we find the correct day? " + theCorrectDay);
		return theCorrectDay;
	}

	@Override
	public List<Day> getSpoilerFreeDays() {

		List<Day> days = getDays();
		List<Day> res = new ArrayList<Day>();

		if (days == null) {
			return null;
		}
		for (Day day : days) {
			if (Utils.isShowSolution(Calendar.getInstance().getTime(),
					day.getSolutionDate())) {
				res.add(day);
			} else if (Utils.isRevealed(Calendar.getInstance().getTime(),
					day.getRevealDate())) {
				day.setSolutionsArtist(null);
				day.setSolutionsSong(null);
				day.setOptionalSolutionVideo(null);
				res.add(day);
			} else {
				day.setLink(null);
				day.setDescription(null);
				day.setSolutionsArtist(null);
				day.setSolutionsSong(null);
				day.setOptionalSolutionVideo(null);
				res.add(day);
			}
		}
		return res;
	}

	@Override
	public Day getToday() {
		Calendar now = Calendar.getInstance();
		Day today = null;

		List<Day> days = getDays();
		for (Day day : days) {
			Calendar calDay = Calendar.getInstance();
			calDay.setTime(day.getRevealDate());
			if (now.get(Calendar.YEAR) == calDay.get(Calendar.YEAR)
					&& now.get(Calendar.MONTH) == calDay.get(Calendar.MONTH)
					&& now.get(Calendar.DATE) == calDay.get(Calendar.DATE)) {
				today = day;
				break;
			}
		}
		return today;
	}

	@Override
	public boolean addDay(Day day) {
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(mongo.getMongoClient(), "musikkjulekalender");
		dataStore.save(day);
		return true;
	}

	@Override
	public boolean updateDay(Day day) {
		LOGGER.debug("Updating or creating day: " + day);
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(mongo.getMongoClient(), "musikkjulekalender");
		
		Day dbDay = getDay(day.getRevealDateAsInt());
		if(dbDay != null) {
			LOGGER.debug("This is apparantly an update of an existing day");
			UpdateOperations<Day> ops;
			Query<Day> updateQuery = dataStore.createQuery(Day.class).field("revealDate").equal(day.getRevealDate());
			ops = dataStore.createUpdateOperations(Day.class).set("description", day.getDescription()).set("link", day.getLink()).set("optionalSolutionVideo", day.getOptionalSolutionVideo()).set("solutionsArtist", day.getSolutionsArtist()).set("solutionsSong", day.getSolutionsSong());
			dataStore.update(updateQuery, ops);
		}
		else {
			LOGGER.debug("This must be a new day?");
			addDay(day);
		}
	
		return true;
	}

	@Override
	public boolean deleteDay(Long dayNumber) {
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(mongo.getMongoClient(), "musikkjulekalender");
	
		dataStore.delete(dataStore.createQuery(Day.class).field("revealDateAsInt").equal(dayNumber).get());
		return true;
	}
}
