package no.saiboten.drumcalendar.day.postgres;

import java.util.ArrayList;
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
	
	private final static Logger logger = LoggerFactory.getLogger(DayServicePostgresImpl.class);

	@Autowired
	public DayServicePostgresImpl(DayRepository dayRepository) {
		this.dayRepository = dayRepository;
	}
	
	@Override
	public List<DayPostgres> getDays() {
		List<DayPostgres> returnDays = new ArrayList<DayPostgres>();
		for(DayPostgres dayp : dayRepository.findAll()) {
			returnDays.add(dayp);
		}
		return returnDays;
	}

	@Override
	public DayPostgres getDay(String dayNumber) {
		return dayRepository.findByRevealDateAsString(dayNumber);
	}

	@Override
	public List<DayPostgres> getSpoilerFreeDays() {
		
		List<DayPostgres> spoilerFreeDays = new ArrayList<DayPostgres>();
		
		List<DayPostgres> days= getDays();
		if(days != null) {
			for(DayPostgres day : days) {
				DateTime dayRevealTime = new DateTime(day.getRevealDateAsString());
				if(dayRevealTime.isBeforeNow()) {
					spoilerFreeDays.add(day);
				}
			}
		}
		
		return spoilerFreeDays;
	}

	@Override
	public DayPostgres getToday() {
		DayPostgres returnDay = null;
		List<DayPostgres> days = getDays();
		if(days != null) {
			for(DayPostgres day : days) {
				DateTime dayAsDayTime = new DateTime(day.getRevealDate());
				DateTime today = new DateTime();
				if(today.withTimeAtStartOfDay().equals(dayAsDayTime.withTimeAtStartOfDay())) {
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
	public boolean deleteDay(String dayNumber) {
		//dayRepository.delete(dayNumber); //FIXME must use day - not id.
		return true;
	}

}
