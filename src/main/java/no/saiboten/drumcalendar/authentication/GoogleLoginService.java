package no.saiboten.drumcalendar.authentication;

import no.saiboten.drumcalendar.utils.GooglePlusLoginResults;

public interface GoogleLoginService {
	public GooglePlusLoginResults login(String oneTimeCode);
}
