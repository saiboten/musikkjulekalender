package no.saiboten.drumcalendar.day;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import no.saiboten.drumcalendar.mongodb.MongoDBClientWrapper;
import no.saiboten.drumcalendar.utils.Utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

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
		List<Day> daysList = new ArrayList<Day>();

		DB db = mongo.getMongoClient().getDB("musikkjulekalender");
		DBCollection daysDb = db.getCollection("days");

		DBCursor dbCursor = daysDb.find();
		for (DBObject dayDbObject : dbCursor) {
			Day day = new Day();
			day.setDescription((String) dayDbObject.get("description"));
			day.setImage((String) dayDbObject.get("image"));
			day.setLink((String) dayDbObject.get("link"));
			day.setOptionalSolutionVideo((String) dayDbObject
					.get("optionalSolutionVideo"));
			day.setRevealDate((Date) dayDbObject.get("revealDate"));
			day.setSolutionDate((Date) dayDbObject.get("solutionDate"));
			day.setSolutionsArtist((List<String>) dayDbObject
					.get("solutionsArtist"));
			day.setSolutionsSong((List<String>) dayDbObject
					.get("solutionsSong"));
			daysList.add(day);
		}
		return daysList;
	}

	@Override
	public Day getDay(Long dayNumber) {
		Day foundIt = null;
		List<Day> days = getDays();
		for (Day day : days) {
			if (dayNumber == day.getRevealDateAsInt()) {
				foundIt = day;
				break;
			}
		}
		return foundIt;
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
		DB db = mongo.getMongoClient().getDB("musikkjulekalender");
		DBCollection days = db.getCollection("days");

		BasicDBObject doc = new BasicDBObject();
		doc.put("description", day.getDescription());
		doc.put("image", day.getImage());
		doc.put("link", day.getLink());
		doc.put("optionalSolutionVideo", day.getOptionalSolutionVideo());
		doc.put("revealDate", day.getRevealDate());
		doc.put("solutionDate", day.getSolutionDate());
		doc.put("solutionsArtist", day.getSolutionsArtist());
		doc.put("solutionsSong", day.getSolutionsSong());

		days.insert(doc);
		return false;
	}

	@Override
	public boolean updateDay(Day day) {
		return false;
	}

	@Override
	public boolean deleteDay(Long dayNumber) {
		DB db = mongo.getMongoClient().getDB("musikkjulekalender");
		DBCollection daysDb = db.getCollection("days");
		
		DBCursor dbCursor = daysDb.find();
		
		for (DBObject dayDbObject : dbCursor) {
			Day day = new Day();
			day.setRevealDate((Date) dayDbObject.get("revealDate"));
			if(day.getRevealDateAsInt() == dayNumber) {
				LOGGER.debug("Found it!");
				daysDb.remove(dayDbObject);
			}
		}
	
		return true;
	}
}
