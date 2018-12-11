package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;
import tacos.Ingredient.Type;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Ingredient findOne(String id) {
		return jdbc.queryForObject("select id, name, type from Ingredient where id = ?", 
				this::mapToIngredient, id);
	}

	@Override
	public List<Ingredient> findAll() {
		return jdbc.query("select id, name, type from Ingredient", 
				this::mapToIngredient);
	}

	@Override
	public void save(Ingredient ingredient) {
		jdbc.update("Insert into Ingredient (id, name, type Values (?, ?, ?)",
				ingredient.getId(),
				ingredient.getName(),
				ingredient.getType());

	}
	
	private Ingredient mapToIngredient(ResultSet rs, int rowNumber) throws SQLException {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(rs.getString("id"));
		ingredient.setName(rs.getString("name"));
		ingredient.setType(Type.valueOf(rs.getString("type")));
		return ingredient;
	}

}
