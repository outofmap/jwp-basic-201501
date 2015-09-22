package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.AnswerDao;
import next.model.Answer;

public class DeleteCommentController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
	private AnswerDao answerdao = new AnswerDao();
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long answerId= ServletRequestUtils.getRequiredLongParameter(request, "answerId");
		answerdao.delete(answerId);
		response.setStatus(HttpServletResponse.SC_OK);
		ModelAndView mav = jsonView();
		return mav;
	}

}
