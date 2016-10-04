package no.saiboten.drumcalendar.answer.postgres;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AnswerRepository extends CrudRepository<AnswerPostgres, Long> {

	public List<AnswerPostgres> findByUserName(String userName);
	
	public List<AnswerPostgres> findByDay(Long userName);
	
	public List<AnswerPostgres> findByUserNameAndDay(String userName, Long day);
	
	@Transactional
	public void deleteByDay(Long day);

}
