package no.saiboten.drumcalendar.utils;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import no.saiboten.drumcalendar.utils.Utils;

import org.junit.Test;
 
public class UtilsTest {

	@Test 
	public void testRemoveAt() {
		assertEquals("mail", Utils.removeAtInMail("mail@domain.net"));
	}

	@Test
	public void testNoAtInMail() {
		assertEquals("userName", Utils.removeAtInMail("userName"));
	}

	@Test
	public void createUuid() {
		for (int i = 0; i < 24; i++) {
			System.out.println(UUID.randomUUID().toString());
		}

	}
}
