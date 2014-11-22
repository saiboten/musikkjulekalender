package no.saiboten.drumcalendar.user;

import org.springframework.stereotype.Component;

@Component
public class CalendarUserServiceImpl {

//	Objectify obj;
//
//	final Logger LOGGER = Logger.getLogger(this.getClass().getName());
//
//	@Autowired
//	public CalendarUserServiceImpl(ObjectifyFactory objectifyFactory) {
//		objectifyFactory.register(CalendarUser.class);
//		objectifyFactory.register(Statistics.class);
//		obj = objectifyFactory.begin();
//	}
//
//	public void putUser(CalendarUser user) {
//		LOGGER.fine("Adding user: " + user);
//		obj.put(user);
//	}
//
//	public CalendarUser getUser(String uid) {
//		LOGGER.fine("Getting user with uid: " + uid);
//		try {
//			return obj.get(CalendarUser.class, uid);
//		} catch (NotFoundException u) {
//			LOGGER.fine("Could not find user with uid: " + uid);
//			return null;
//		}
//	}
//
//	public void deleteUser(String uid) {
//		LOGGER.fine("Deleting user: " + uid);
//		CalendarUser user = getUser(uid);
//		LOGGER.fine("The user to be deleted: " + user);
//		obj.delete(user);
//	}
//
//	public void deleteAnswersUser(String uid) {
//		LOGGER.fine("Deleting answers for user: " + uid);
//		CalendarUser user = getUser(uid);
//		user.setAnswers(new HashMap<Long, Answer>());
//		putUser(user);
//	}
//
//	@Override
//	public List<CalendarUser> getAllUsers() {
//		LOGGER.fine("Getting all users.");
//		QueryResultIterable<CalendarUser> q = obj.query(CalendarUser.class).fetch();
//
//		List<CalendarUser> res = new ArrayList<CalendarUser>();
//
//		if (q == null) {
//			LOGGER.fine("Query for users returned nothing? Strange.");
//			return null;
//		}
//		for (CalendarUser calUser : q) {
//			res.add(calUser);
//		}
//		return res;
//	}
//
//	@Override
//	public UserStatistics getUserStatistics(String uid) {
//		UserStatistics userStats = new UserStatistics();
//		CalendarUser calendarUser = getUser(uid);
//		Map<Long, Answer> answers = calendarUser.getAnswers();
//
//		int correctAnswersArtist = 0;
//		int correctAnswersSong = 0;
//
//		int answersArtist = 0;
//		int answersSong = 0;
//
//		for (Answer answer : answers.values()) {
//			if (answer.isRevealAnswer()) {
//				if (answer.isCorrectArtist()) {
//					correctAnswersArtist++;
//				}
//				if (answer.isCorrectSong()) {
//					correctAnswersSong++;
//				}
//				answersArtist++;
//				answersSong++;
//			}
//		}
//
//		userStats.setCorrectAnswersArtist(correctAnswersArtist);
//		userStats.setCorrectAnswersSong(correctAnswersSong);
//
//		userStats.setTotalAnswersArtist(answersArtist);
//		userStats.setTotalAnswersSong(answersSong);
//
//		return userStats;
//	}
//
//	@Override
//	public void deleteAllUsers() {
//		LOGGER.fine("Deleting all users");
//		List<CalendarUser> users = getAllUsers();
//
//		for (CalendarUser user : users) {
//			LOGGER.fine("Deleting user " + user);
//			deleteUser(user.getUserName());
//		}
//	}
//
//	@Override
//	public void fixSong(String mail, Long day) {
//		CalendarUser user = getUser(mail);
//		if (user != null) {
//			user.incrementRightSong();
//			Map<Long, Answer> answers = user.getAnswers();
//
//			Answer answer = answers.get(day);
//			if (answer != null) {
//				answer.setCorrectSong(true);
//			}
//			putUser(user);
//		}
//	}
//
//	@Override
//	public void fixArtist(String mail, Long day) {
//		CalendarUser user = getUser(mail);
//		if (user != null) {
//
//			user.incrementRightArtist();
//
//			Map<Long, Answer> answers = user.getAnswers();
//
//			Answer answer = answers.get(day);
//			if (answer != null) {
//				answer.setCorrectArtist(true);
//			}
//			putUser(user);
//		}
//	}
}
