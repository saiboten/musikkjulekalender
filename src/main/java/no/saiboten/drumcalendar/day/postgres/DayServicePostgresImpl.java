package no.saiboten.drumcalendar.day.postgres;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import no.saiboten.drumcalendar.day.service.DayService;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayServicePostgresImpl implements DayService {

	private DayRepository dayRepository;

	private final static Logger logger = LoggerFactory
			.getLogger(DayServicePostgresImpl.class);

	@Autowired
	public DayServicePostgresImpl(DayRepository dayRepository) {
		this.dayRepository = dayRepository;
	}

	@Override
	public List<DayPostgres> getDays() {
		List<DayPostgres> returnDays = new ArrayList<DayPostgres>();
		for (DayPostgres dayp : dayRepository.findAll()) {
			returnDays.add(dayp);
		}
		
		Collections.sort(returnDays, new Comparator<DayPostgres>() {

			@Override
			public int compare(DayPostgres arg0, DayPostgres arg1) {
				// TODO Auto-generated method stub
				if(arg0.getRevealDate().before(arg1.getRevealDate())) {
					return -1;
				}
				return 1;
			}
		});
		return returnDays;
	}

	@Override
	public DayPostgres getDay(String dayNumber) {
		return dayRepository.findByRevealDateAsString(dayNumber);
	}

	@Override
	public List<DayPostgres> getSpoilerFreeDays() {

		List<DayPostgres> spoilerFreeDays = new ArrayList<DayPostgres>();

		List<DayPostgres> days = getDays();
		if (days != null) {
			for (DayPostgres day : days) {
				DateTime dayRevealTime = new DateTime(
						day.getRevealDateAsString());
				DateTime solution = new DateTime(day.getSolutionDate());
				
				logger.debug("Now: " + new DateTime());
				logger.debug("solution: " + solution);
				logger.debug("reveal date: " + dayRevealTime);

				

				if (solution.isBeforeNow()) { // hide everything
					// Show everything
					logger.debug("We show everything");

				} else if (dayRevealTime.isBeforeNow()) { // show task, but not answer
					logger.debug("We show only task");

					day.setSolutionArtist(null);
					day.setSolutionSong(null);
					day.setOptionalSolutionVideo(null);
				}
				else {
					logger.debug("We show nothing");
					day.setDifficulty(null);
					day.setCooperator(null);
					day.setSolutionArtist(null);
					day.setSolutionSong(null);
					day.setOptionalSolutionVideo(null);
					day.setDescription(null);
					day.setImage(null);
					day.setLink(null);
				}
				
				spoilerFreeDays.add(day);
			}
		}

		return spoilerFreeDays;
	}

	@Override
	public DayPostgres getToday() {
		DayPostgres returnDay = null;
		List<DayPostgres> days = getDays();
		if (days != null) {
			for (DayPostgres day : days) {
				DateTime dayAsDayTime = new DateTime(day.getRevealDate());
				DateTime today = new DateTime();
				if (today.withTimeAtStartOfDay().equals(
						dayAsDayTime.withTimeAtStartOfDay())) {
					returnDay = day;
				}
			}
		}

		return returnDay;
	}

	@Override
	public boolean addDay(DayPostgres day) {
		logger.debug("Day: " + day);
		DayPostgres res = dayRepository.save(day);
		return res != null;
	}

	@Override
	public boolean updateDay(DayPostgres day) {
		DayPostgres res = dayRepository.save(day);
		return res != null;
	}

	@Override
	public boolean deleteDay(Long dayNumber) {
		dayRepository.deleteById(dayNumber);
		return true;
	}

}
