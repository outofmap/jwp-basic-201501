package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.Question;

public class JdbcAnswerDao implements AnswerDao {
	private JdbcTemplate jdbctemplate;
	public JdbcAnswerDao(JdbcTemplate jdbctemplate){
		this.jdbctemplate = jdbctemplate;
	}
	
	public void insert(Answer answer) {
		String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
		jdbctemplate.update(sql, answer.getWriter(), answer.getContents(),
				new Timestamp(answer.getTimeFromCreateDate()), answer.getQuestionId());
	}

	public List<Answer> findAllByQuestionId(long questionId) {
		String sql = "SELECT answerId, writer, contents, createdDate FROM ANSWERS WHERE questionId = ? "
				+ "order by answerId desc";

		RowMapper<Answer> rm = new RowMapper<Answer>() {
			@Override
			public Answer mapRow(ResultSet rs) throws SQLException {
				return new Answer(rs.getLong("answerId"), rs.getString("writer"), rs.getString("contents"),
						rs.getTimestamp("createdDate"), questionId);
			}
		};

		return jdbctemplate.query(sql, rm, questionId);
	}

	public void delete(long answerId) {
		String sql = "DELETE from ANSWERS where answerId = ?";
		jdbctemplate.update(sql, answerId);

	}
	public void deleteByQuestionId(long questionId) {
		String sql = "DELETE from ANSWERS where questionId = ?";
		jdbctemplate.update(sql, questionId);
	}
	
	public Answer findByAnswerId(long answerId) {
		String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS " + "WHERE answerId = ?";

		RowMapper<Answer> rm = new RowMapper<Answer>() {
			@Override
			public Answer mapRow(ResultSet rs) throws SQLException {
				return new Answer(rs.getLong("answerId"), rs.getString("writer"), rs.getString("contents"),
						rs.getTimestamp("createdDate"), rs.getLong("questionId"));
			}
		};

		return jdbctemplate.queryForObject(sql, rm, answerId);
	}
	
	
}
