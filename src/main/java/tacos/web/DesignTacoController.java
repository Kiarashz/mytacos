package tacos.web;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
		Iterable<Ingredient> iIngredients = ingredientRepository.findAll();
		for (Type type: Ingredient.Type.values()) {
			Stream<Ingredient> sIngredients = StreamSupport.stream(iIngredients.spliterator(), true);
			model.addAttribute(type.name().toLowerCase(), 
					sIngredients.filter(ing -> ing.getType().equals(type))
					.collect(Collectors.toList())
					);
		}
		
		model.addAttribute("taco", new Taco());
		return "design";
	}
}
