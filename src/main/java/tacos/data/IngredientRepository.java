package tacos.data;

import tacos.Ingredient;

public interface IngredientRepository {

	Ingredient findOne(String id);
	Iterable<Ingredient> findAll();

}
