package core.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import next.controller.APIDeleteController;
import next.controller.AddAnswerController;
import next.controller.DeleteCommentController;
import next.controller.DeleteQuestionController;
import next.controller.ListController;
import next.controller.QuestionListAPI;
import next.controller.SaveController;
import next.controller.ShowController;
import next.dao.AnswerDao;
import next.dao.JdbcAnswerDao;
import next.dao.JdbcQuestionDao;
import next.dao.QuestionDao;
import next.service.ServiceDao;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	private Map<String, Controller> mappings = new HashMap<String, Controller>();
	
	public void initMapping() {
		DataSource dataSource = ConnectionManager.getDataSource();
		JdbcTemplate jdbctemplate = new JdbcTemplate(dataSource);
		AnswerDao answerdao = new JdbcAnswerDao(jdbctemplate);
		QuestionDao questiondao = new JdbcQuestionDao(jdbctemplate);
		
		ServiceDao servicedao = new ServiceDao(questiondao,answerdao);
		mappings.put("/list.next", new ListController(questiondao));
		mappings.put("/show.next", new ShowController(questiondao, answerdao));
		mappings.put("/form.next", new ForwardController("form.jsp"));
		mappings.put("/save.next", new SaveController(questiondao));
		mappings.put("/deleteComment.next", new DeleteCommentController(answerdao,questiondao));
		mappings.put("/api/addanswer.next", new AddAnswerController(answerdao,questiondao));
		mappings.put("/api/list.next", new QuestionListAPI(questiondao));
		mappings.put("/delete.next", new DeleteQuestionController(servicedao));
		mappings.put("/api/delete.next", new APIDeleteController(servicedao));
		
		
		
		logger.info("Initialized Request Mapping!");
	}

	public Controller findController(String url) {
		return mappings.get(url);
	}

	void put(String url, Controller controller) {
		mappings.put(url, controller);
	}

}
