package tacos.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;
import tacos.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {

	private JdbcTemplate jdbc;
	private KeyGenerator keyGenerator;

	private String INSERT_TACO_SQL = "insert into Taco (id, name, createdAt) values (?, ?, ?)";
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc, KeyGenerator generator) {
		super();
		this.jdbc = jdbc;
		this.keyGenerator = generator;
	}


	@Override
	public Taco save(Taco design) {
		// Save design
		design.setCreatedAt(new Date());
		design.setId(keyGenerator.nextKey());
		jdbc.update(INSERT_TACO_SQL, 
				design.getId(),
				design.getName(),
				new Timestamp(design.getCreatedAt().getTime()));

		for (Ingredient ing: design.getIngredients()) {
			jdbc.update("insert into Taco_Ingredients (taco, ingredient) values (?, ?)",
					design.getId(), 
					ing.getId());
		}
		return design;
	}

}
