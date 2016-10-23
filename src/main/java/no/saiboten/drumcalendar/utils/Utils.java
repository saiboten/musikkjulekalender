package no.saiboten.drumcalendar.utils;

public class Utils {
	public static String removeAtInMail(String mail) {

		if (mail == null) {
			return null;
		}

		String[] mailInPieces = mail.split("@");
		if (mailInPieces != null & mailInPieces.length > 0) {
			return mailInPieces[0];
		}
		return null;
	}
}
