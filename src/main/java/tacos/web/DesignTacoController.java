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
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepository;
	private TacoRepository tacoRepository;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
		this.ingredientRepository = ingredientRepository;
		this.tacoRepository = tacoRepository;
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String design(Model model) {
		log.info("Rendering Taco design form");
		updateModel(model);
		return "design";
	}
	
	private void updateModel(Model model) {
		Iterable<Ingredient> iIngredients = ingredientRepository.findAll();
		for (Type type : Ingredient.Type.values()) {
			Stream<Ingredient> sIngredients = StreamSupport.stream(iIngredients.spliterator(), true);
			model.addAttribute(type.name().toLowerCase(),
					sIngredients.filter(ing -> ing.getItype().equals(type)).collect(Collectors.toList()));
		}		
	}

	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors, Model model, @ModelAttribute Order order) {
		log.info("Processing designed taco");
	    if (errors.hasErrors()) {
	    	updateModel(model);
	    	// TODO: errors are not added to model
	        return "design";
	      }
		tacoRepository.save(taco);
		order.getTacos().add(taco);
		return "redirect:/orders/current";
	}
}
