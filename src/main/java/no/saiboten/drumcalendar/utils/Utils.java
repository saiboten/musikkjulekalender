package no.saiboten.drumcalendar.utils;

import java.util.Date;

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

	public static boolean isRevealed(Date now, Date revealDate) {
		if (now.after(revealDate)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isShowSolution(Date now, Date showSolutionDate) {
		if (now.after(showSolutionDate)) {
			return true;
		} else {
			return false;
		}
	}
}
