package no.saiboten.drumcalendar.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	private final Logger LOGGER = Logger.getLogger(getClass());
	
	@Override
	public Date convert(String arg0) {
		Date res = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		try {
			res = sdf.parse(arg0);
		} catch (ParseException e) {
			LOGGER.warn("Could not parse date: " + e.getMessage());
		}
		return res;
	}

}
