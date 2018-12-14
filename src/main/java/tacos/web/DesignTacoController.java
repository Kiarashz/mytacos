package tacos.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.data.IngredientRepository;

@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepository;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@GetMapping
	public String design(Model model) {
		List<Ingredient> ingredients = ingredientRepository.findAll();
		
		for (Type type: Ingredient.Type.values()) {
			model.addAttribute(type.name(), 
					ingredients.stream().filter(ing -> ing.getType().equals(type)));
		}
		
		model.addAttribute("taco", new Taco());
		return "design";
	}
}
