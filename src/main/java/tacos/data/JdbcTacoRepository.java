package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;
import tacos.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {

	private JdbcTemplate jdbc;
	
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		super();
		this.jdbc = jdbc;
	}



	@Override
	public Taco save(Taco design) {
		// Save design
		jdbc.update("insert into Taco (id, name, createdAt) values (?, ?, ?)",
				design.getId(),
				design.getName(),
				design.getCreatedAt());
		
		for (Ingredient ing: design.getIngredients()) {
			jdbc.update("insert into Taco_Ingredients (taco, ingredient) values (?, ?)",
					design.getId(), 
					ing.getId());
		}
		return null;
	}

}
