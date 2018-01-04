package watmoetiketen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import watmoetiketen.Gerecht;
import watmoetiketen.Ingredient;

//@Slf4j
@CrossOrigin
@RestController
public class Controller {

	private IngredientRepo ingredientRepo;
	private GerechtRepo gerechtRepo;

	@Autowired
	public Controller(
			IngredientRepo ingredientRepo,
			GerechtRepo gerechtRepo) {
		this.ingredientRepo = ingredientRepo;
		this.gerechtRepo = gerechtRepo;
	}

	@RequestMapping("/")
	public String index() {
		return "c'est la banana, mjam, mjam";
	}

	@GetMapping(value = "/get/ingredient")
	public Ingredient geefIngredient() {
		return ingredientRepo.geefIngredient();
	}

	@PostMapping(value = "/post/ingredient")
	public ResponseEntity<Ingredient> postIngredient(@RequestBody Ingredient ingredient) {
		HttpStatus status = ingredientRepo.postNieuwIngredient(ingredient);
		return new ResponseEntity<Ingredient>(ingredient, status);
	}
	
	@PostMapping(value = "/post/gerecht")
	public ResponseEntity<Gerecht> postGerecht(@RequestBody Gerecht gerecht) {
		HttpStatus status = gerechtRepo.postNieuwGerecht(gerecht);
		return new ResponseEntity<Gerecht>(gerecht, status);
	}
	
	@GetMapping(value = "/get/gerecht")
	public Gerecht geefGerecht() {
		return gerechtRepo.geefGerecht();
	}

}