package no.saiboten.drumcalendar.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.saiboten.drumcalendar.mongodb.MongoDBClientWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Component
public class CalenderUserServiceMongoImpl implements CalendarUserService {

	private MongoDBClientWrapper mongoDBClientWrapper;

	private final Logger LOGGER = Logger.getLogger(getClass());
	
	@Autowired
	public CalenderUserServiceMongoImpl(MongoDBClientWrapper mongoDBClientWrapper) {
		this.mongoDBClientWrapper = mongoDBClientWrapper;
	}
	
	@Override
	public void putUser(CalendarUser user) {
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(mongoDBClientWrapper.getMongoClient(), "musikkjulekalender");
		dataStore.save(user);
	}

	@Override
	public CalendarUser getUser(String uid) {
		LOGGER.debug("Getting user: " + uid);
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(mongoDBClientWrapper.getMongoClient(), "musikkjulekalender");
		CalendarUser theCorrectUser = dataStore.find(CalendarUser.class).field("userName").equal(uid).get();
		LOGGER.debug("Did we find the correct user? " + theCorrectUser);
		return theCorrectUser;
	}

	@Override
	public void deleteAllUsers() {
		DB db = mongoDBClientWrapper.getMongoClient().getDB("musikkjulekalender");
		DBCollection users = db.getCollection("users");
		DBCursor userCursor = users.find();
		
		for (DBObject dbObject : userCursor) {
			users.remove(dbObject);
		}
	}

	@Override
	public void deleteUser(String uid) {
		DB db = mongoDBClientWrapper.getMongoClient().getDB("musikkjulekalender");
		DBCollection users = db.getCollection("users");
		DBCursor userCursor = users.find();
		
		DBObject dbObjectToBeRemoved = null;
		
		for (DBObject dbObject : userCursor) {
			CalendarUser user = (CalendarUser) dbObject.get("user");
			if(StringUtils.equals(uid, user.getUserName())) {
				dbObjectToBeRemoved = dbObject;
				break;
			}
		}
		users.remove(dbObjectToBeRemoved);
	}

	@Override
	public List<CalendarUser> getAllUsers() {
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(mongoDBClientWrapper.getMongoClient(), "musikkjulekalender");
		List<CalendarUser> allUsers = dataStore.find(CalendarUser.class).asList();
		LOGGER.debug("Did we find all the users? " + allUsers);
		return allUsers;
	}

	@Override
	public UserStatistics getUserStatistics(String uid) {
		UserStatistics userStats = new UserStatistics();
		CalendarUser calendarUser = getUser(uid);
		Map<Long, Answer> answers = calendarUser.getAnswers();

		int correctAnswersArtist = 0;
		int correctAnswersSong = 0;

		int answersArtist = 0;
		int answersSong = 0;

		for (Answer answer : answers.values()) {
			if (answer.isRevealAnswer()) {
				if (answer.isCorrectArtist()) {
					correctAnswersArtist++;
				}
				if (answer.isCorrectSong()) {
					correctAnswersSong++;
				}
				answersArtist++;
				answersSong++;
			}
		}

		userStats.setCorrectAnswersArtist(correctAnswersArtist);
		userStats.setCorrectAnswersSong(correctAnswersSong);

		userStats.setTotalAnswersArtist(answersArtist);
		userStats.setTotalAnswersSong(answersSong);

		return userStats;
	}

	@Override
	public void deleteAnswersUser(String uid) {
		LOGGER.debug("Deleting answers for user: " + uid);
		CalendarUser user = getUser(uid);
		user.setAnswers(new HashMap<Long, Answer>());
		putUser(user);
	}

	@Override
	public void fixSong(String mail, Long day) {
		CalendarUser user = getUser(mail);
		if (user != null) {
			user.incrementRightSong();
			Map<Long, Answer> answers = user.getAnswers();

			Answer answer = answers.get(day);
			if (answer != null) {
				answer.setCorrectSong(true);
			}
			putUser(user);
		}
	}

	@Override
	public void fixArtist(String mail, Long day) {
		CalendarUser user = getUser(mail);
		if (user != null) {

			user.incrementRightArtist();

			Map<Long, Answer> answers = user.getAnswers();

			Answer answer = answers.get(day);
			if (answer != null) {
				answer.setCorrectArtist(true);
			}
			putUser(user);
		}
	}

}
