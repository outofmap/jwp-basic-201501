package next.service;

import java.util.List;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.Result;

public class ServiceDao {
	private QuestionDao questiondao;
	private AnswerDao answerdao;

	public ServiceDao(QuestionDao questiondao, AnswerDao answerdao) {
		this.questiondao = questiondao;
		this.answerdao = answerdao;
	}

	public void delete(long questionId) throws Exception {
		Question question = questiondao.findById(questionId);

		if (question == null) {
			throw new Exception("질문이 존재하지 않아 삭제할 수 없습니다.");
		}

		// 댓글이 없는 경우, 질문 삭제 가능
		List<Answer> answers = answerdao.findAllByQuestionId(questionId);
		if (answers.isEmpty()) {
			questiondao.delete(questionId);
			return;
		}

		// 질문자 이외의 댓글 작성자가 존재하는 경우, 질문 삭제 불가
		boolean canDelete = question.canDelete();
		
		if (canDelete) {
			questiondao.delete(questionId);
			answerdao.deleteByQuestionId(questionId);
			return;
		}
	}

	public Question findById(long questionId) {
		return questiondao.findById(questionId);
	}

	public List<Answer> findAllByQuestionId(long questionId) {
		return answerdao.findAllByQuestionId(questionId);
	}
	
	
}
