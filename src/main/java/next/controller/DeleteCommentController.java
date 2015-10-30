package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class DeleteCommentController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
	private AnswerDao answerdao;
	private QuestionDao questiondao;
	private Answer answer;
	private List<Answer> answers;
	private Question question;
	
	public DeleteCommentController(AnswerDao answerdao, QuestionDao questiondao) {
		super();
		this.answerdao = answerdao;
		this.questiondao = questiondao;
	}

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long answerId= ServletRequestUtils.getLongParameter(request, "answerId");
		logger.debug("answerID :"+answerId);
		answer = answerdao.findByAnswerId(answerId);
		questiondao.minusCountOfComment(answer.getQuestionId());
		answerdao.delete(answerId);
	
		
		ModelAndView mav = jstlView("redirect:/show.jsp");
		
		answers = answerdao.findAllByQuestionId(answer.getQuestionId());
		mav.addObject("answers", answers);
		mav.addObject("question", questiondao.findById(answer.getQuestionId()));
		
		return mav;
	}

}
