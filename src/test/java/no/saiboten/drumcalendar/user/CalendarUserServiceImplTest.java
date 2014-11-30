package no.saiboten.drumcalendar.user;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;

public class CalendarUserServiceImplTest {
	@Mock
	private Objectify obj;

	@Mock
	CalendarUser user;

	@Mock
	ObjectifyFactory objectifyFactory;

	private CalendarUserServiceImpl calendarUserServiceImpl;

	public CalendarUserServiceImplTest() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(objectifyFactory.begin()).thenReturn(obj);
		calendarUserServiceImpl = new CalendarUserServiceImpl(objectifyFactory);
	}

	@Test
	public void testPutUser() {
		calendarUserServiceImpl.putUser(user);
		Mockito.verify(obj, Mockito.times(1)).put(user);
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
