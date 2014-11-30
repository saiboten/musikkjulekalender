package no.saiboten.drumcalendar.utils;

import java.util.UUID;

import org.junit.Test;

public class Testy {

	@Test
	public void createUuid() {
		for (int i = 0; i < 24; i++) {
			System.out.println(UUID.randomUUID().toString());
		}

	}
}
