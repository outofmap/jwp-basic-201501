package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.WebServerLauncher;
import next.service.ServiceDao;

public class DeleteQuestionController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(WebServerLauncher.class);
	private ServiceDao servicedao;
	
	public DeleteQuestionController(ServiceDao servicedao) {
		this.servicedao = servicedao;
	}

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");

		try {
			servicedao.delete(questionId);
			return jstlView("redirect:/list.next");
		} catch (Exception e) {
			ModelAndView mav = jstlView("show.jsp");
			mav.addObject("question", servicedao.findById(questionId));
			mav.addObject("answers", servicedao.findAllByQuestionId(questionId));
			mav.addObject("errorMessage", e.getMessage());
			logger.error(e.getMessage());
			return mav;
		}
	}
}
