package no.saiboten.drumcalendar.authentication;

import no.saiboten.drumcalendar.utils.GooglePlusLoginResults;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
public class AuthenticationController {

	private final Logger LOGGER = Logger.getLogger(getClass());

	private GoogleLoginService googleLoginService;
	private FacebookLoginService facebookLoginService;

	@Autowired
	public AuthenticationController(GoogleLoginService googleLoginService,
			FacebookLoginService facebookLoginService) {
		this.googleLoginService = googleLoginService;
		this.facebookLoginService = facebookLoginService;

	}

	@RequestMapping("/logmeon")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("logmein", "active");
		return mav;
	}

	@RequestMapping("/plus")
	public ModelAndView pluslogin(@RequestBody String code) {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJackson2JsonView());
		LOGGER.debug("Login in using Google plus");
		GooglePlusLoginResults googlePlusLoginResults = googleLoginService
				.login(code);
		mav.addObject("result", googlePlusLoginResults.toString());
		return mav;

	}

	@RequestMapping(value = "/facebooklogin", method = RequestMethod.POST)
	public ModelAndView facebookLoginService(@RequestBody String accessToken) {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJackson2JsonView());

		LOGGER.debug("Login in using Facebook");
		mav.addObject("result", facebookLoginService.login(accessToken));
		return mav;

	}
}
