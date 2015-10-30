package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.model.Result;
import next.service.ServiceDao;

public class APIDeleteController extends AbstractController {
	private ServiceDao servicedao;

	public APIDeleteController(ServiceDao servicedao) {
		this.servicedao = servicedao;
	}

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		
		ModelAndView mav = jsonView();
		try{
		servicedao.delete(questionId);
		mav.addObject("result", Result.ok());
		}catch(Exception e){ 
		mav.addObject("result", Result.fail(e.getMessage())); 
		}
		return mav;
	}
}
