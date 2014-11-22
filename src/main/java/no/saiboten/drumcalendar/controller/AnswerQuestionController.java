package no.saiboten.drumcalendar.controller;

import java.util.Calendar;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import no.saiboten.drumcalendar.day.Day;
import no.saiboten.drumcalendar.day.DayService;
import no.saiboten.drumcalendar.user.Answer;
import no.saiboten.drumcalendar.user.CalendarUser;
import no.saiboten.drumcalendar.user.CalendarUserService;
import no.saiboten.drumcalendar.user.LoggedInRequestHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnswerQuestionController {

	final Logger LOGGER = Logger.getLogger(AnswerQuestionController.class.getName());

	@Autowired
	private LoggedInRequestHolder loggedIn;

	@Autowired
	private CalendarUserService cus;

	@Autowired
	private DayService dayService;

	@RequestMapping("/answer")
	public ModelAndView answerQuestion(@RequestParam(value = "song") String song,
			@RequestParam(value = "artist") String artist) {
		ModelAndView mav = new ModelAndView("answergiven");

		if (song == null || song.equals("") || artist == null || artist == "") {
			mav.addObject("feedback", "Enten sang eller artist var tom. Vennligst prøv igjen..");
			return mav;
		}

		song = song.trim();
		artist = artist.trim();

		Pattern pattern = Pattern.compile("(\\w|\\s|[0-9]|\\?|\\&|ø|æ|å)+");
		Matcher matchArtist = pattern.matcher(artist);
		Matcher matchSong = pattern.matcher(song);
		if (!matchArtist.matches() || !matchSong.matches()) {
			mav.addObject(
					"feedback",
					"Forslaget inneholdt ulovlige tegn. Kun bokstaver og tall er tillatt. Dersom svaret ditt inneholder spesielle tegn, kan du fjerne disse, vi har i så fall gjort det samme i fasiten");
			return mav;
		}

		CalendarUser user = loggedIn.getCalendarUser();
		Day today = dayService.getToday();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today.getRevealDate());

		LOGGER.fine("Date is: " + cal.getTimeInMillis() + ". User is: " + user);
		if (user != null) {
			Answer answer = new Answer();
			answer.setAnswerArtist(artist);
			answer.setAnswerSong(song);
			answer.setDay(cal.getTimeInMillis());
			user.addAnswer(cal.getTimeInMillis(), answer);
			cus.putUser(user);
			LOGGER.fine("User updated with new answer: " + artist + ": " + song + " ");
			mav.addObject("feedback", "Svar gitt: " + song + " av " + artist);
		} else {
			mav.addObject("feedback", "Noe gikk galt. Er du innlogget?");
		}

		return mav;
	}

	@RequestMapping("answermobile")
	public ModelAndView answerMobile(@RequestParam(value = "song") String song,
			@RequestParam(value = "artist") String artist) {
		ModelAndView mav = answerQuestion(song, artist);
		mav.setViewName("/mobile/answergiven");
		return mav;
	}

	@RequestMapping(value = "/admin/answerCheat", method = RequestMethod.GET)
	public ModelAndView answerQuestionGet() {
		ModelAndView mav = new ModelAndView("cheat");
		return mav;
	}

	@RequestMapping(value = "/admin/answerCheat", method = RequestMethod.POST)
	public ModelAndView answerQuestion(@RequestParam(value = "song") String song,
			@RequestParam(value = "artist") String artist, @RequestParam(value = "day") Long day) {
		ModelAndView mav = new ModelAndView("answergiven");

		CalendarUser user = loggedIn.getCalendarUser();
		Long date = day;
		LOGGER.fine("Date is: " + date + ". User is: " + user);
		if (user != null) {
			Answer answer = new Answer();
			answer.setAnswerArtist(artist);
			answer.setAnswerSong(song);
			answer.setDay(date);
			user.addAnswer(date, answer);
			cus.putUser(user);
			LOGGER.fine("User updated with new answer: " + artist + ": " + song + " ");
			mav.addObject("feedback", "Svar gitt: " + song + " av " + artist);
		} else {
			mav.addObject("feedback", "Noe gikk galt. Er du innlogget?");
		}

		return mav;
	}
}
