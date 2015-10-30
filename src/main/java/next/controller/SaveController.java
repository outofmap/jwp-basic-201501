package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.QuestionDao;
import next.model.Question;

public class SaveController extends AbstractController{
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
	private Question question;
	private QuestionDao questionDao;
	
	public SaveController(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String writer = ServletRequestUtils.getRequiredStringParameter(request,"writer");
		String title = ServletRequestUtils.getRequiredStringParameter(request,"title"); 
		String contents = ServletRequestUtils.getRequiredStringParameter(request,"contents"); 
		question = new Question(writer, title, contents);
		questionDao.insert(question);
		
		ModelAndView mav = jstlView("redirect:list.next");
		return mav;
	}
}
