package no.saiboten.drumcalendar.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import no.saiboten.drumcalendar.utils.Utils;

//@Component
public class DayServiceImpl implements DayService {
	private List<Day> days;

	private final Logger LOGGER = Logger.getLogger(DayServiceImpl.class.getName());

	public DayServiceImpl() {
		days = new ArrayList<Day>();
		fillWithDays();
	}

	private void fillWithDays() {

		days.add(createDay(createDayTemplate(1),
				"Vi åpner kalenderen med en rockelåt spilt av Bjarte! Kjent låt fra kjent band.",
				"a51c90ce-32b0-4f39-b78d-4d487927faae", "Iron Maiden",
				Arrays.asList("Run to the Hills!")));
		days.add(createDay(createDayTemplate(2), "I denne låten er vokalisten sta. Spilt av Tobias",
				"cffc3630-e910-4e0b-8fb9-9cc4ca312f81", "Rage Against the Machine",
				Arrays.asList("Killing in the Name", "Killing in the Name of")));
		days.add(createDay(createDayTemplate(3), "Legendarisk trommebeat, denne gangen portrettert av Bjarte.",
				"4cf43a09-e5c8-4752-8505-3d2e24ea3d50", "Toto", Arrays.asList("Rosanna")));
		days.add(createDay(
				createDayTemplate(4),
				"Eirik har laget svartmetallåter av kjente og kjære låter. Her er første ut, hører du hvilken låt som covres?",
				"88a5842a-3be9-4943-80cc-16f2cfcf2b32",
				"Backstreet Boys",
				Arrays.asList("Everybody", "Everybody Backstreets back"),
				"<iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/6M6samPEMpM\" frameborder=\"0\" allowfullscreen></iframe>"));
		days.add(createDay(
				createDayTemplate(5),
				"En britisk hit fra 1980 med en ganske psykedelisk musikkvideo. Klippet er laget av Stein.",
				"620f17b7-9e6d-4022-953e-f2901bc8d6d3",
				"David Bowie",
				Arrays.asList("Ashes to Ashes"),
				"<iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/CMThz7eQ6K0\" frameborder=\"0\" allowfullscreen></iframe>"));
		days.add(createDay(
				createDayTemplate(6),
				"Tittelspor fra legendarisk skive. Bjarte har trommet inn denne for oss. ",
				"4fc9e601-f30f-429d-ac52-206cf0dbe671",
				"Metallica",
				Arrays.asList("And Justice For All", "Justice For All"),
				"<iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/92miS5edOao\" frameborder=\"0\" allowfullscreen></iframe>"));
		days.add(createDay(
				createDayTemplate(7),
				"Denne kjente låten er delvis spilt i en serie om noen gutter fra Colorado. Tobias sitter bak trommsettet på denne.",
				"67c0f64d-5058-4329-8d4a-d41e750cb583",
				"Kansas",
				Arrays.asList("Carry on Wayward Son", "Carry on My Wayward Son"),
				"<iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/6d0YgX15BhQ\" frameborder=\"0\" allowfullscreen></iframe>"));
		days.add(createDay(
				createDayTemplate(8),
				"En rockelåt fra 80-tallet som de fleste bør dra kjensel på. Sangen handler om et sted man vil helst vil være. Lydklippet er spilt inn av Bjarte",
				"2fa314f2-89f2-4bf7-aad0-42dbebc98be5", "Guns N Roses",
				Arrays.asList("Paradise City")));

		days.add(createDay(
				createDayTemplate(9),
				"En poplåt fra det kontinentale Europa. Stein står som ansvarlig",
				"bb122073-f6e9-4fca-9e25-818a97c5add1",
				"Phoenix",
				Arrays.asList("1901"),
				"<iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/pe4gQTABqNA\" frameborder=\"0\" allowfullscreen></iframe>"));

		days.add(createDay(
				createDayTemplate(10),
				"Eirik er nok en gang ute og lager svartmetallutgaver av kjente låter. Gjenkjenner du klassikeren fra 90-tallet?",
				"d240891b-0e04-4fbe-8104-cd4fb0c4fbe9",
				"East 17",
				Arrays.asList("Stay Another Day"),
				"<iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/aNm9rDeTT8E\" frameborder=\"0\" allowfullscreen></iframe>"));

		days.add(createDay(
				createDayTemplate(11),
				"Et gjenforent rockeorkester med aktiv vokalist. Bjarte på slagverket",
				"4d4ac955-97ad-44e6-9c8b-c8b46e86efca",
				"Soundgarden",
				Arrays.asList("Spoonman", "Spoon man"),
				"<iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/T0_zzCLLRvE\" frameborder=\"0\" allowfullscreen></iframe>"));

		days.add(createDay(
				createDayTemplate(12),
				"Kubjelle! Hvor kult er ikke det? Bjarte har nok en gang bidratt med dette flotte klippet",
				"7c6c5330-6a1e-45bb-a393-f6ee4fdfa827",
				"Twisted Sisters",
				Arrays.asList("Were Not Gonna Take It", "We are Not Gonna Take It"),
				"<iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/MotNtq41NDw\" frameborder=\"0\" allowfullscreen></iframe>"));

		days.add(createDay(
				createDayTemplate(13),
				"Dette sporet er til ære for en nylig avdød legende. Innspilt av Tobias",
				"794bd7c3-326c-43cb-8487-8951c224bce9",
				"Dio",
				Arrays.asList("Holy Diver"),
				"<iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/bkysjcs5vFU\" frameborder=\"0\" allowfullscreen></iframe>"));

		days.add(createDay(
				createDayTemplate(14),
				"Bassisten er kjent med band og soloartist! Denne gangen med band. Tobias trakterer trommesettet",
				"3873cd6d-3ebe-4864-938d-a5e0c61f02d5",
				"The Police",
				Arrays.asList("Cant Stand Losing You", "Cant Stand Losing"),
				"<iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/nH0vjLwMyc4\" frameborder=\"0\" allowfullscreen></iframe>"));

		days.add(createDay(createDayTemplate(15),
				"Stein har laget denne plankeoppgaven til oss! Går det for raskt for deg? Ikke sakt ned.",
				"f6d2731a-5ceb-4078-8ae9-6de67222c973", "The Strokes",
				Arrays.asList("Reptilia")));

		days.add(createDay(
				createDayTemplate(16),
				"Hard musikk, som du hører. Det kontinentale europa står nok en gang sentralt. Bjarte bidrar med småfrekk tromming",
				"125d7530-99f6-4c99-9735-491346382ba3", "Gojira", Arrays.asList("Toxic Garbage Island")));

		days.add(createDay(
				createDayTemplate(17),
				"Vi får ta en vanskelig til, siden det er helg. En låt som er en liten musikkquiz i seg selv. Stein har laget denne.",
				"f7f9225d-4230-4f6f-8b39-1ef71d8d5384", "Build to Spill",
				Arrays.asList("You Were Right")));

		days.add(createDay(createDayTemplate(18),
				"En låt fra tidlig 90-tallet. Låten finnes på Rock Band eller Guitar Hero. Spilt av Tobias",
				"e3dca888-1431-4af2-a61f-1000e8abd615", "Megadeth",
				Arrays.asList("Holy wars the punishment due", "holy wars", "punishment due")));

		days.add(createDay(createDayTemplate(19), "Popballade med rockedel fra nyere tider. Tobias har laget denne.",
				"83ba2ba8-dab1-4377-810c-b254a8f95d15", "Coldplay",
				Arrays.asList("Fix You")));

		days.add(createDay(createDayTemplate(20),
				"Tobias spiller en låt fra et kritikerrost band som har blitt større i løpet av siste tiden",
				"b9c64d1c-aeaa-4e29-9db1-8f8eade7740b", "Kvelertak",
				Arrays.asList("Fossegrim", "Fosse grim")));

		days.add(createDay(
				createDayTemplate(21),
				"En låt fra en artist som har vært med tidligere i kalenderen. Bjarte har laget dette flotte trommesporet.",
				"4181f202-6125-44da-a986-7238cd03abbd", "Sting", Arrays.asList("I Hung My Head")));

		days.add(createDay(createDayTemplate(22),
				"Beklager. Ingen hint. Utenom at låten er fra et ganske så kjent band. Spilt av Tobias",
				"229af941-6409-4e06-9f0a-e8fcf09772d2", "Radiohead",
				Arrays.asList("Subterranean Homesick Alien", "Subterraneon Homesick Alien")));

		days.add(createDay(createDayTemplate(29), "En sang om blomster og fremgang? Tobias bak settet, igjen!",
				"d3c0f032-84a6-4e0e-aff3-58b09bebcf0b", "Nirvana", Arrays.asList("In Bloom")));

		days.add(createDay(
				createDayTemplate(30),
				"God jul! Dette er ikke en julelåt, men en stor låt fra et kjent band. Litt eldre, tung rock. Spilt av Tobias",
				"ca0aca3b-4dd5-4ebe-b06c-8c7d9d046935", "Black Sabbath", Arrays.asList("Iron Man")));
	}

	private Day createDay(Day day, String description, String link, String solutionsArtist,
			List<String> solutionsSong, String optionalVideo) {
		day.setOptionalSolutionVideo(optionalVideo);
		return createDay(day, description, link, solutionsArtist, solutionsSong);
	}

	private Day createDay(Day day, String description, String link, String solutionsArtist,
			List<String> solutionsSong) {
		day.setDescription(description);
		day.setLink("http://www.saiboten.com/drumcalendar/" + link + ".mp3");
		day.setSolutionArtist(solutionsArtist);
		day.setSolutionsSong(solutionsSong);
		return day;
	}

	public Day createDayTemplate(int startDate) {
		Day day = new Day();

		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();

		cal.set(2012, Calendar.SEPTEMBER, startDate - 1, 23, 59);
		Date revealDate = cal.getTime();
		day.setRevealDate(revealDate);

		cal.set(2012, Calendar.SEPTEMBER, startDate, 23, 59);
		Date showSolutionDate = cal.getTime();
		day.setSolutionDate(showSolutionDate);
		return day;
	}

	public List<Day> getDays() {
		return days;
	}

	public Day getToday() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Day> getSpoilerFreeDays() {
		List<Day> daysWithSpoilers = getDays();
		List<Day> daysWithoutSpoilers = new ArrayList<Day>();
		for (Day day : daysWithSpoilers) {
			if (Utils.isShowSolution(Calendar.getInstance().getTime(), day.getSolutionDate())) {
				daysWithoutSpoilers.add(day);
			} else {
				day.setSolutionArtist(null);
				day.setSolutionsSong(null);
				daysWithoutSpoilers.add(day);
			}
		}
		return daysWithoutSpoilers;
	}

	@Override
	public boolean addDay(Day day) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Day getDay(Long dayNumber) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteDay(Long dayNumber) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean updateDay(Day day) {
		// TODO Auto-generated method stub
		return false;
	}

}
