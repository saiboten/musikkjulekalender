package no.saiboten.drumcalendar.solution;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SolutionRepository extends CrudRepository<Solution, Long> {
	public List<Solution> findByDay(Long day);
	
    @Transactional
	public void deleteByDay(Long day);
    
    @Transactional
	public void deleteByDayAndSolution(Long day, String solution);
}
