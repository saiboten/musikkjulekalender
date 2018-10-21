package no.saiboten.drumcalendar.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MusikkjulekalenderExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(MusikkjulekalenderExceptionHandler.class);
	
	 @ExceptionHandler(value = Exception.class)
	  public ModelAndView handleError(HttpServletRequest req, Exception exception) {
	    logger.debug("Request: " + req.getRequestURL() + " raised " + exception);

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", exception.getMessage());
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName("error");
	    return mav;
	  }
}
