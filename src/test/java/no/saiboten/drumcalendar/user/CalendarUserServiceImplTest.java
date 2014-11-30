package no.saiboten.drumcalendar.user;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CalendarUserServiceImplTest {
	
	@Mock
	CalendarUser user;

	private CalendarUserServiceImpl calendarUserServiceImpl;

	public CalendarUserServiceImplTest() {
		MockitoAnnotations.initMocks(this);
//		calendarUserServiceImpl = new CalendarUserServiceImpl(objectifyFactory);
	}

//	@Test
	public void testPutUser() {
//		calendarUserServiceImpl.putUser(user);
//		Mockito.verify(obj, Mockito.times(1)).put(user);
	}

	@Test
	public void testGetUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAnswersUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserStatistics() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAllUsers() {

	}

}
