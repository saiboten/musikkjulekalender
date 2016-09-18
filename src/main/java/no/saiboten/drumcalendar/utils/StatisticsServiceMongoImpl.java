package no.saiboten.drumcalendar.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import no.saiboten.drumcalendar.day.DayService;
import no.saiboten.drumcalendar.mongodb.MongoDBClientWrapper;
import no.saiboten.drumcalendar.user.Answer;
import no.saiboten.drumcalendar.user.CalendarUser;
import no.saiboten.drumcalendar.user.CalendarUserService;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticsServiceMongoImpl implements StatisticsService {

	DayService dayService;

	CalendarUserService userService;

    Logger logger = LoggerFactory.getLogger(StatisticsServiceMongoImpl.class);

	private MongoDBClientWrapper mongoDBClientWrapper;

	@Autowired
	public StatisticsServiceMongoImpl(CalendarUserService userService,
			DayService dayService, MongoDBClientWrapper mongoDBClientWrapper) {
		this.userService = userService;
		this.dayService = dayService;
		this.mongoDBClientWrapper = mongoDBClientWrapper;
	}

	@Override
	public Statistics getStatistics() {
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(
				mongoDBClientWrapper.getMongoClient(), "musikkjulekalender2015");
		Statistics winners = dataStore.find(Statistics.class).get();
		return winners;
	}

	@Override
	public void putStatistics(Statistics stats) {
		Morphia morphia = new Morphia();
		Datastore dataStore = morphia.createDatastore(
				mongoDBClientWrapper.getMongoClient(), "musikkjulekalender2015");
		dataStore.save(stats);
	}

	@Override
	public void updateStatsForDay(Long day) {
		Statistics stats = getStatistics();
		Map<Long, Boolean> activationMap = stats.getActivatedDays();
		activationMap.put(day, true);

		Map<Long, OneDayStat> theDay = stats.getDayMap();
		OneDayStat oneDayStat = populateDay(day);
		theDay.put(day, oneDayStat);
		putStatistics(stats);
	}

	private OneDayStat populateDay(Long day) {
		OneDayStat oneDayStat = new OneDayStat();

		int rightSong = 0;
		int totalSong = 0;

		List<CalendarUser> users = userService.getAllUsers();
		for (CalendarUser user : users) {
			Map<Long, Answer> answers = user.getAnswers();
			if (answers != null) {
				Answer answer = answers.get(day);
				if (answer != null) {
					if (answer.isRevealAnswer()) {
						if (answer.isCorrectSong()) {
							rightSong++;
						}
						totalSong++;
					}
				}
			}
		}

		oneDayStat.setCorrectSong(rightSong);
		oneDayStat.setTotalSong(totalSong);

		return oneDayStat;
	}

	public List<CalendarUser> getBestUsers() {
		List<CalendarUser> users = userService.getAllUsers();

		int worstScoreInBestList = 0;

		LinkedList<CalendarUser> bestUsers = new LinkedList<CalendarUser>();
		for (CalendarUser user : users) {
			if (user.getTotalScore() == 0) {
				logger.debug("Score is 0 for user " + user.getUserName()
						+ ". Not eligible");
			} else if (bestUsers.size() < 5) {
				logger.debug("The list of best users are smaller than 3, and this user has score more than one:"
						+ user.getUserName() + ": " + user.getTotalScore());
				bestUsers.add(user);
				if (user.getTotalScore() < worstScoreInBestList) {
					worstScoreInBestList = user.getTotalScore();
				}
			} else {
				if (user.getTotalScore() > worstScoreInBestList) {
					logger.debug("The user has a score better than the worst in the best list, adding user: "
							+ user.getUserName() + ": " + user.getTotalScore());
					bestUsers.add(user);
					Collections.sort(bestUsers);
					bestUsers.removeLast();
				}
			}
		}
		return bestUsers;
	}

	@Override
	public void deleteStatistics() {
		// TODO impl
	}

}
