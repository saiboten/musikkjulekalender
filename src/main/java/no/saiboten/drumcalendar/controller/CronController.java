package no.saiboten.drumcalendar.controller;

import no.saiboten.drumcalendar.cron.DayUpdaterCron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CronController {

	private DayUpdaterCron dayUpdaterCron;

	@Autowired
	public CronController(DayUpdaterCron dayUpdaterCron) {
		this.dayUpdaterCron = dayUpdaterCron;
		
	}

	@RequestMapping("/admin/updateScores/{day}")
	public RedirectView updateScoresDay(@PathVariable(value = "day") Long day) {
		dayUpdaterCron.updateScoresDay(day);
		return new RedirectView("/");
	}

}
