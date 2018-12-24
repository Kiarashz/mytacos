package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcKeyGenerator implements KeyGenerator {

	private Long key;
	private JdbcTemplate jdbc;
	private final String startFrom = "100";

	@Autowired
	public JdbcKeyGenerator(JdbcTemplate jdbcTemplate) {
		this.jdbc = jdbcTemplate;
		try {
			this.key = jdbc.queryForObject("select nextKey from Keys", Long.class);
		} catch (DataAccessException sqlException) {
			var newKey = Long.decode(startFrom);
			key = save(newKey, true);
		}
	}

	private Long save(Long newKey, boolean insert) {
		if (insert) {
			jdbc.execute("delete from Keys");
			jdbc.update("Insert into Keys (nextKey) values (?)", newKey);
		} else {
			jdbc.update("Update Keys set nextKey = ?", newKey);
		}

		return newKey;
	}

	@Override
	public Long nextKey() {
		Long result = key;
		save(++key, false);
		return result;
	}

}
