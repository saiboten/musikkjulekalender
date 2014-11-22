package no.saiboten.drumcalendar.service.impl;

import java.util.logging.Logger;

import no.saiboten.drumcalendar.bean.WinnersDbBean;

import org.springframework.stereotype.Component;

@Component
public class WinnerDao {

	private final Logger LOGGER = Logger.getLogger(WinnerDao.class.getName());

	public WinnerDao() {

	}

	public WinnersDbBean getWinners() {
		WinnersDbBean winners = null;

		if (winners == null) {
			winners = new WinnersDbBean();
			winners.setKey("winner");
		}
		return winners;
	}

	public void saveWinners(WinnersDbBean winners) {

	}

}
