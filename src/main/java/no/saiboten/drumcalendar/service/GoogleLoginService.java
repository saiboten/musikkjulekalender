package no.saiboten.drumcalendar.service;

import no.saiboten.drumcalendar.utils.GooglePlusLoginResults;

public interface GoogleLoginService {
	public GooglePlusLoginResults login(String oneTimeCode);
}
