package no.saiboten.drumcalendar.user;

import javax.validation.ReportAsSingleViolation;

import jdk.nashorn.internal.runtime.options.LoggingOption.LoggerInfo;
import no.saiboten.drumcalendar.admin.bean.GenericResponse;
import no.saiboten.drumcalendar.user.postgres.CalendarUserPostgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	private CalendarUserService calendarUserService;
	private LoggedInRequestHolder loggedInRequestHolder;

	@Autowired
	public UserController(LoggedInRequestHolder loggedInRequestHolder, CalendarUserService calendarUserService) {
		this.loggedInRequestHolder = loggedInRequestHolder;
		this.calendarUserService = calendarUserService;
	}
	
	@RequestMapping("/api/setnickname/{nickname}")
	public @ResponseBody GenericResponse setUsername(@PathVariable String nickname) throws Exception {
		CalendarUserPostgres dbUser = calendarUserService.getUser(loggedInRequestHolder.getUserName());
		if(dbUser == null) {
			throw new Exception("Fant ikke bruker i database. Noe er alvorlig galt?");
		}
		dbUser.setNickName(nickname);
		calendarUserService.putUser(dbUser);
		GenericResponse response = new GenericResponse();
		response.success = true;
		response.feedback = "Brukernavn oppdatert";
		return response;
	}
}
