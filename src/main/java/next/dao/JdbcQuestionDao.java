package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.dao.QuestionDao;
import next.model.Question;

public class JdbcQuestionDao implements QuestionDao {
	private JdbcTemplate jdbcTemplate;
	private JdbcAnswerDao jdbcanswerdao;
	
	public JdbcQuestionDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insert(Question question) {
		String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfComment) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents(),
				new Timestamp(question.getTimeFromCreateDate()), question.getCountOfComment());
	}

	public List<Question> findAll() {
		String sql = "SELECT questionId, writer, title, createdDate, countOfComment FROM QUESTIONS "
				+ "order by questionId desc";

		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), null,
						rs.getTimestamp("createdDate"), rs.getInt("countOfComment"));
			}

		};

		return jdbcTemplate.query(sql, rm);
	}

	public Question findById(long questionId) {
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfComment FROM QUESTIONS "
				+ "WHERE questionId = ?";

		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
						rs.getString("contents"), rs.getTimestamp("createdDate"), rs.getInt("countOfComment"));
			}

		};

		return jdbcTemplate.queryForObject(sql, rm, questionId);
	}

	public void addCountOfComment(long questionId) {
		String sql = "UPDATE QUESTIONS SET countOfComment = countOfComment + 1 WHERE questionId = ?";
		jdbcTemplate.update(sql, questionId);

	}

	public void minusCountOfComment(long questionId) {
		String sql = "UPDATE QUESTIONS SET countOfComment = countOfComment - 1 WHERE questionId = ?";
		jdbcTemplate.update(sql, questionId);

	}

	public void delete(long questionId) {
		String sql = "DELETE from QUESTIONS where questionId = ?";
		jdbcTemplate.update(sql, questionId);
	}
	
	public Question findWithAnswersById(long questionId) throws Exception{
		Question question = findById(questionId);
		if (question == null) {
			throw new Exception("존재하지 않는 질문입니다.");
		}
		question.setAnswers(jdbcanswerdao.findAllByQuestionId(questionId));
		return question;
	}
}
