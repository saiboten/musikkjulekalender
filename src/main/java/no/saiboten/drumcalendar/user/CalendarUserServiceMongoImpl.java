package no.saiboten.drumcalendar.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.saiboten.drumcalendar.answer.Answer;
import no.saiboten.drumcalendar.mongodb.MongoDBClientWrapper;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Component
public class CalendarUserServiceMongoImpl implements CalendarUserService {

	private MongoDBClientWrapper mongoDBClientWrapper;

    Logger logger = LoggerFactory.getLogger(CalendarUserServiceMongoImpl.class);

	@Autowired
	public CalendarUserServiceMongoImpl(MongoDBClientWrapper mongoDBClientWrapper) {
		this.mongoDBClientWrapper = mongoDBClientWrapper;
	}
	
	@Override
	public void putUser(CalendarUser user) {
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(mongoDBClientWrapper.getMongoClient(), "musikkjulekalender2015");
		dataStore.save(user);
	}

	@Override
	public CalendarUser getUser(String uid) {
		logger.debug("Getting user: " + uid);
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(mongoDBClientWrapper.getMongoClient(), "musikkjulekalender2015");
		CalendarUser theCorrectUser = dataStore.find(CalendarUser.class).field("userName").equal(uid).get();
		logger.debug("Did we find the correct user? " + theCorrectUser);
		return theCorrectUser;
	}

	@Override
	public void deleteAllUsers() {
		DB db = mongoDBClientWrapper.getMongoClient().getDB("musikkjulekalender2015");
		DBCollection users = db.getCollection("users");
		DBCursor userCursor = users.find();
		
		for (DBObject dbObject : userCursor) {
			users.remove(dbObject);
		}
	}

	@Override
	public void deleteUser(String uid) {
		DB db = mongoDBClientWrapper.getMongoClient().getDB("musikkjulekalender2015");
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
		Datastore dataStore = morphia.createDatastore(mongoDBClientWrapper.getMongoClient(), "musikkjulekalender2015");
		List<CalendarUser> allUsers = dataStore.find(CalendarUser.class).asList();
		logger.debug("Did we find all the users? " + allUsers);
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
		logger.debug("Deleting answers for user: " + uid);
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
	public void fixSongScore(String mail, Long day, int score) {
		CalendarUser user = getUser(mail);
		if (user != null) {

			user.setRightSong(score);
			putUser(user);
		}
	}

	@Override
	public void setSongAnswer(String mail, Long day, String song) {
		CalendarUser user = getUser(mail);
		if (user != null) {
			Map<Long, Answer> answers = user.getAnswers();
			Answer answer = answers.get(day);
			if(answer == null) {
				answer = new Answer();
			}
			answer.setAnswerSong(song);
			answers.put(day, answer);
			user.setAnswers(answers);
			putUser(user);
		}
	}

}
