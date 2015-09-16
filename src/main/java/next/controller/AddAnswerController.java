package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.AnswerDao;
import next.model.Answer;

public class AddAnswerController extends AbstractController {
	private Answer answer;
	private AnswerDao answerdao = new AnswerDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String writer;
		String contents;
		long questionId = ServletRequestUtils.getRequiredLongParameter(request, " questionId");
		
		return null;
	}

}
