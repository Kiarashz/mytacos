package tacos.web;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

@Controller
@RequestMapping("/design")
public class DesignTacoController {

	private final IngredientRepository ingredientRepository;
	private TacoRepository tacoRepository;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
		this.ingredientRepository = ingredientRepository;
		this.tacoRepository = tacoRepository;
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String design(Model model) {
		updateGetModel(model);
		return "design";
	}
	
	private void updateGetModel(Model model) {
		Iterable<Ingredient> iIngredients = ingredientRepository.findAll();
		for (Type type : Ingredient.Type.values()) {
			Stream<Ingredient> sIngredients = StreamSupport.stream(iIngredients.spliterator(), true);
			model.addAttribute(type.name().toLowerCase(),
					sIngredients.filter(ing -> ing.getType().equals(type)).collect(Collectors.toList()));
		}		
	}

	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors, Model model) {
	    if (errors.hasErrors()) {
	    	updateGetModel(model);
	        return "design";
	      }
		tacoRepository.save(taco);
		return "orders";
	}
}
