package tacos.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

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
	
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		super();
		this.jdbc = jdbc;
		pscf = new
				PreparedStatementCreatorFactory(
				"insert into Taco (name, createdAt) values (?, ?)",
				Types.VARCHAR, Types.TIMESTAMP);
	}


	@Override
	public Taco save(Taco design) {
		// Save design
		design.setCreatedAt(new Date());
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
				Arrays.asList(
						design.getName(), 
						new Timestamp(design.getCreatedAt().getTime())
						));
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(psc, keyHolder);
		Number key = keyHolder.getKey(); 
		long id = key.longValue();
		design.setId(id);

		for (Ingredient ing: design.getIngredients()) {
			jdbc.update("insert into Taco_Ingredients (taco, ingredient) values (?, ?)",
					design.getId(), 
					ing.getId());
		}
		return design;
	}

}
