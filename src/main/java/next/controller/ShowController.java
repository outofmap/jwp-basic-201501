package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class ShowController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
	
	private QuestionDao questionDao;
	private AnswerDao answerdao;
	private Question question;
	
	public ShowController(QuestionDao questiondao, AnswerDao answerdao){
		super();
		this.questionDao = questiondao;
		this.answerdao = answerdao;
	}
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Answer> answers;
		long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		logger.debug("questionId : {}", questionId);
		question = questionDao.findById(questionId);
		answers = answerdao.findAllByQuestionId(questionId);
		ModelAndView mav = jstlView("show.jsp");
		mav.addObject("question", question);
		mav.addObject("answers", answers);
		return mav;
	}
}
