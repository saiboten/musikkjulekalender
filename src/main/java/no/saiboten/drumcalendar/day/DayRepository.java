package no.saiboten.drumcalendar.day;
import org.springframework.data.repository.CrudRepository;

public interface DayRepository extends CrudRepository<DayPostgres, Long> {

	DayPostgres findByRevealDateAsInt(Long revealDate);

}
