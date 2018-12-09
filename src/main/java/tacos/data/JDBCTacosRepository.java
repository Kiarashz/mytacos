package tacos.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;

@Repository
public class JDBCTacosRepository implements TacosRepository {
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JDBCTacosRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Ingredient findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingredient> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Ingredient ingredient) {
		// TODO Auto-generated method stub

	}

}
