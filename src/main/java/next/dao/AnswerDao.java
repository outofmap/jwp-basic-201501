package next.dao;

import java.util.List;

import next.model.Answer;

public interface AnswerDao {
	public void insert(Answer answer);
	List<Answer> findAllByQuestionId(long questionId);
	void delete(long answerId);
	void deleteByQuestionId(long questionId);
	Answer findByAnswerId(long answerId);
}
