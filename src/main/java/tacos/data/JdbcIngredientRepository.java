package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
		return jdbc.queryForObject("select iid, name, itype from Ingredients where iid = ?", 
				this::mapToIngredient, id);
	}

	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("select iid, name, itype from Ingredients", 
				this::mapToIngredient);
	}

	private Ingredient mapToIngredient(ResultSet rs, int rowNumber) throws SQLException {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(rs.getString("iid"));
		ingredient.setName(rs.getString("name"));
		ingredient.setType(Type.valueOf(rs.getString("itype")));
		return ingredient;					
	}

}
