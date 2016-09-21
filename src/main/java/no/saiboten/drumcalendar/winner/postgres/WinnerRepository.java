package no.saiboten.drumcalendar.winner.postgres;

import org.springframework.data.repository.CrudRepository;

public interface WinnerRepository extends CrudRepository<WinnerPostgres, Long> {
	public WinnerPostgres findByDay(String day);
}
