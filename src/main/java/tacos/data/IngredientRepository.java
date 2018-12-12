package tacos.data;

import java.util.List;

import org.springframework.stereotype.Repository;

import tacos.Ingredient;

public interface IngredientRepository {

	Ingredient findOne(String id);
	List<Ingredient> findAll();
	void save(Ingredient ingredient);

}
