package no.saiboten.drumcalendar.solution;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SolutionRepository extends CrudRepository<Solution, Long> {
	public List<Solution> findByDay(Long day);
}
