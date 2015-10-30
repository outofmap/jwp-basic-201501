package next.controller;

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

public class AddAnswerController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
	
	private Answer answer;
	private AnswerDao answerdao;
	private QuestionDao questiondao;
	
	public AddAnswerController(AnswerDao answerdao, QuestionDao questiondao) {
		super();
		this.answerdao = answerdao;
		this.questiondao = questiondao;
	}

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String writer;
		String contents;
		long questionId;
		
		writer = ServletRequestUtils.getStringParameter(request, "writer");
		contents = ServletRequestUtils.getStringParameter(request, "contents");
		questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		answer = new Answer(writer, contents, questionId);
		answerdao.insert(answer);
		questiondao.addCountOfComment(questionId);
		logger.debug(answer.toString());
		response.setStatus(HttpServletResponse.SC_OK);
		ModelAndView mav = jsonView();
		return mav;
	}

}
