package tacos.data;

import java.util.List;

import tacos.Ingredient;

public interface IngredientRepository {

	Ingredient findOne(String id);
	List<Ingredient> findAll();
	void save(Ingredient ingredient);

}
