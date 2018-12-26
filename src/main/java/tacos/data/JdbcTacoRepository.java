package tacos.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;
import tacos.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {

	private JdbcTemplate jdbc;
	private PreparedStatementCreatorFactory pscf;
	private String INSERT_TACO_SQL = "insert into Tacos (name, createdAt) values (?, ?)";
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		super();
		this.jdbc = jdbc;
		pscf = new PreparedStatementCreatorFactory(INSERT_TACO_SQL,
				Types.VARCHAR, Types.TIMESTAMP);
		pscf.setReturnGeneratedKeys(true);
	}


	@Override
	public Taco save(Taco design) {
		// Save design
		design.setCreatedAt(new Date());
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
				Arrays.asList(
				design.getName(), 
				new Timestamp(design.getCreatedAt().getTime())));
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(psc, keyHolder);
		design.setId(keyHolder.getKey().longValue());

		for (Ingredient ing: design.getIngredients()) {
			jdbc.update("insert into Ingredients_Tacos (taco, ingredient) values (?, ?)",
					design.getId(), 
					ing.getId());
		}
		return design;
	}

}
