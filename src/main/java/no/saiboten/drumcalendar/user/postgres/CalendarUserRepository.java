package no.saiboten.drumcalendar.user.postgres;

import org.springframework.data.repository.CrudRepository;

public interface CalendarUserRepository extends CrudRepository<CalendarUserPostgres, Long> {
	public CalendarUserPostgres findByUserName(String userName);
}
