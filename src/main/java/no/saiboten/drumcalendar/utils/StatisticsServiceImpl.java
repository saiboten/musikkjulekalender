package no.saiboten.drumcalendar.utils;

import org.springframework.stereotype.Component;

@Component
public class StatisticsServiceImpl {

//	private Objectify obj;
//
//	DayService dayService;
//
//	CalendarUserService userService;
//
//	UserService googleUserService;
//
//	private final Logger LOGGER = Logger.getLogger(StatisticsServiceImpl.class.getName());
//
//	@Autowired
//	public StatisticsServiceImpl(CalendarUserService userService, DayService dayService) {
//		ObjectifyService.register(Statistics.class);
//		obj = ObjectifyService.begin();
//		googleUserService = UserServiceFactory.getUserService();
//		this.userService = userService;
//		this.dayService = dayService;
//	}
//
//	@Override
//	public Statistics getStatistics() {
//		try {
//			return obj.get(Statistics.class, "mysingleton");
//		} catch (Exception u) {
//			u.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public void putStatistics(Statistics stats) {
//		obj.put(stats);
//	}
//
//	@Override
//	public void updateStatsForDay(Long day) {
//		Statistics stats = getStatistics();
//		Map<Long, Boolean> activationMap = stats.getActivatedDays();
//		activationMap.put(day, true);
//
//		Map<Long, OneDayStat> theDay = stats.getDayMap();
//		OneDayStat oneDayStat = populateDay(day);
//		theDay.put(day, oneDayStat);
//		putStatistics(stats);
//	}
//
//	private OneDayStat populateDay(Long day) {
//		OneDayStat oneDayStat = new OneDayStat();
//
//		int rightArtist = 0;
//		int totalArtist = 0;
//		int rightSong = 0;
//		int totalSong = 0;
//
//		List<CalendarUser> users = userService.getAllUsers();
//		for (CalendarUser user : users) {
//			Map<Long, Answer> answers = user.getAnswers();
//			if (answers != null) {
//				Answer answer = answers.get(day);
//				if (answer != null) {
//					if (answer.isRevealAnswer()) {
//						if (answer.isCorrectArtist()) {
//							rightArtist++;
//						}
//						if (answer.isCorrectSong()) {
//							rightSong++;
//						}
//						totalArtist++;
//						totalSong++;
//					}
//				}
//			}
//		}
//
//		oneDayStat.setCorrectArtist(rightArtist);
//		oneDayStat.setTotalArtist(totalArtist);
//		oneDayStat.setCorrectSong(rightSong);
//		oneDayStat.setTotalSong(totalSong);
//
//		return oneDayStat;
//	}
//
//	public List<CalendarUser> getBestUsers() {
//		List<CalendarUser> users = userService.getAllUsers();
//
//		int worstScoreInBestList = 0;
//
//		LinkedList<CalendarUser> bestUsers = new LinkedList<CalendarUser>();
//		for (CalendarUser user : users) {
//			if (user.getTotalScore() == 0) {
//				LOGGER.fine("Score is 0 for user " + user.getUserName() + ". Not eligible");
//			} else if (bestUsers.size() < 5) {
//				LOGGER.fine("The list of best users are smaller than 3, and this user has score more than one:"
//						+ user.getUserName() + ": " + user.getTotalScore());
//				bestUsers.add(user);
//				if (user.getTotalScore() < worstScoreInBestList) {
//					worstScoreInBestList = user.getTotalScore();
//				}
//			} else {
//				if (user.getTotalScore() > worstScoreInBestList) {
//					LOGGER.fine("The user has a score better than the worst in the best list, adding user: "
//							+ user.getUserName() + ": " + user.getTotalScore());
//					bestUsers.add(user);
//					Collections.sort(bestUsers);
//					bestUsers.removeLast();
//				}
//			}
//		}
//		return bestUsers;
//	}
//
//	@Override
//	public void deleteStatistics() {
//		Statistics stats = obj.get(Statistics.class, "mysingleton");
//		obj.delete(stats);
//	}

}
