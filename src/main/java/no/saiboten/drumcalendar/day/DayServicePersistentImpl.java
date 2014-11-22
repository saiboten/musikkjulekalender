package no.saiboten.drumcalendar.day;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import no.saiboten.drumcalendar.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.google.appengine.api.datastore.QueryResultIterable;
//import com.googlecode.objectify.Objectify;
//import com.googlecode.objectify.ObjectifyFactory;

@Component
public class DayServicePersistentImpl {
//
////	private Objectify obj;
//
//	final Logger LOGGER = Logger.getLogger(this.getClass().getName());
//
//	@Autowired
//	public DayServicePersistentImpl(ObjectifyFactory objectifyFactory) {
//		objectifyFactory.register(Day.class);
//		obj = objectifyFactory.begin();
//	}
//
//	@Override
//	public List<Day> getDays() {
//		LOGGER.fine("Getting all days.");
//		QueryResultIterable<Day> q = obj.query(Day.class).fetch();
//
//		List<Day> res = new ArrayList<Day>();
//
//		if (q == null) {
//			LOGGER.fine("Query for days returned nothing? Strange.");
//			return null;
//		}
//		for (Day day : q) {
//			res.add(day);
//		}
//		return res;
//	}
//
//	@Override
//	public List<Day> getSpoilerFreeDays() {
//		LOGGER.fine("Getting all days.");
//		QueryResultIterable<Day> q = obj.query(Day.class).fetch();
//
//		List<Day> res = new ArrayList<Day>();
//
//		if (q == null) {
//			LOGGER.fine("Query for days returned nothing? Strange.");
//			return null;
//		}
//		for (Day day : q) {
//			if (Utils.isShowSolution(Calendar.getInstance().getTime(), day.getSolutionDate())) {
//				res.add(day);
//			} else if (Utils.isRevealed(Calendar.getInstance().getTime(), day.getRevealDate())) {
//				day.setSolutionsArtist(null);
//				day.setSolutionsSong(null);
//				day.setOptionalSolutionVideo(null);
//				res.add(day);
//			} else {
//				day.setLink(null);
//				day.setDescription(null);
//				day.setSolutionsArtist(null);
//				day.setSolutionsSong(null);
//				day.setOptionalSolutionVideo(null);
//				res.add(day);
//			}
//		}
//		return res;
//	}
//
//	@Override
//	public Day getToday() {
//		Calendar now = Calendar.getInstance();
//		Day today = null;
//
//		List<Day> days = getDays();
//		for (Day day : days) {
//			Calendar calDay = Calendar.getInstance();
//			calDay.setTime(day.getRevealDate());
//			if (now.get(Calendar.YEAR) == calDay.get(Calendar.YEAR)
//					&& now.get(Calendar.MONTH) == calDay.get(Calendar.MONTH)
//					&& now.get(Calendar.DATE) == calDay.get(Calendar.DATE)) {
//				today = day;
//				break;
//			}
//		}
//
//		return today;
//	}
//
//	@Override
//	public boolean addDay(Day day) {
//		obj.put(day);
//		return true;
//	}
//
//	@Override
//	public Day getDay(Long dayNumber) {
//		Day day = obj.get(Day.class, dayNumber);
//		return day;
//	}
//
//	@Override
//	public boolean deleteDay(Long dayNumber) {
//		obj.delete(Day.class, dayNumber);
//		return true;
//	}
//
//	@Override
//	public boolean updateDay(Day day) {
//		obj.put(day);
//		return false;
//	}

}
