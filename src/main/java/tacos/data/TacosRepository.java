package tacos.data;

import java.util.List;

import org.springframework.stereotype.Repository;

import tacos.Ingredient;

@Repository
public interface TacosRepository {
	
	Ingredient findOne(String id);
	List<Ingredient> findAll();
	void save(Ingredient ingredient);

}
