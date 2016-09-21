package no.saiboten.drumcalendar.day.controller;

import java.util.List;

import no.saiboten.drumcalendar.admin.bean.GenericResponse;
import no.saiboten.drumcalendar.day.bean.Day;
import no.saiboten.drumcalendar.day.postgres.DayPostgres;
import no.saiboten.drumcalendar.day.service.DayService;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DayController {
	
	private DayService dayService;

    Logger logger = LoggerFactory.getLogger(DayController.class);
	
	@Autowired
	public DayController(DayService dayService) {
		this.dayService = dayService;
	}
	
	
	
	@RequestMapping(value = "/admin/day", method = RequestMethod.POST)
	public @ResponseBody GenericResponse addDay(@RequestBody Day day) {
		GenericResponse response = new GenericResponse();

		boolean success = dayService.addDay(convertToPostgresDay(day));
		if (success) {
			response.setSuccess(true);
			response.setFeedback("La til ny dag! Great success!");

		} else {
			response.setSuccess(false);
			response.setFeedback("Noe gikk galt. Sjekk loggen!");
		}

		return response;
	}
	
	private DayPostgres convertToPostgresDay(Day day) {
		DayPostgres dayPostgres = new DayPostgres();
		dayPostgres.setDescription(day.getDescription());
		dayPostgres.setImage(day.getImage());
		dayPostgres.setLink(day.getLink());
		dayPostgres.setOptionalSolutionVideo(day.getOptionalSolutionVideo());
		dayPostgres.setRevealDate(day.getRevealDate());
		DateTime solutionDate = new DateTime(day.getRevealDate()).plusDays(1);
		dayPostgres.setSolutionDate(solutionDate.toDate());
		 DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		dayPostgres.setRevealDateAsString(fmt.print(new DateTime(day.getRevealDate())));
		dayPostgres.setSolutionArtist(day.getSolutionArtist());
		dayPostgres.setSolutionSong(day.getSolutionSong());
		return dayPostgres;
	}

	@RequestMapping(value = "/admin/day", method = RequestMethod.PUT)
	public @ResponseBody GenericResponse updateDay(@RequestBody DayPostgres day) {
		GenericResponse response = new GenericResponse();

		boolean success = dayService.updateDay(day);
		if (success) {
			response.setSuccess(true);
			response.setFeedback("Oppdaterte dag! Great success!");

		} else {
			response.setSuccess(false);
			response.setFeedback("Noe gikk galt. Sjekk loggen!");
		}

		return response;
	}
	
	@RequestMapping(value = "/admin/day/{id}", method = RequestMethod.GET)
	public @ResponseBody DayPostgres getDay(@PathVariable("id") String revealDateAsString) {
		logger.debug("Getting day: " + revealDateAsString);
		return dayService.getDay(revealDateAsString);
	}
	
	@RequestMapping(value = "/admin/days", method = RequestMethod.GET)
	public @ResponseBody List<DayPostgres> getDays(Long revealDateAsInt) {
		return dayService.getDays();
	}
	
	
	@RequestMapping(value = "/admin/day/{day}", method = RequestMethod.DELETE)
	public @ResponseBody GenericResponse deleteDay(@PathVariable(value="day") String revealDateAsString) {
		GenericResponse response = new GenericResponse();

		boolean success = dayService.deleteDay(revealDateAsString);
		if (success) {
			response.setSuccess(true);
			response.setFeedback("Slettet dag. Great success!");

		} else {
			response.setSuccess(false);
			response.setFeedback("Noe gikk galt. Sjekk loggen!");
		}

		return response;
	}
}
