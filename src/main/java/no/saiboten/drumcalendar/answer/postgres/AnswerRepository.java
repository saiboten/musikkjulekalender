package no.saiboten.drumcalendar.answer.postgres;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<AnswerPostgres, Long> {

	public List<AnswerPostgres> findByUserName(String userName);
	
	public AnswerPostgres findByUserNameAndDay(String userName, String day);
}
