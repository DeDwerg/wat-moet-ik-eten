package watmoetiketen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import watmoetiketen.Ingredient;

//@Slf4j
@RestController
public class Controller {

    private IngredientRepo repo;

    @Autowired
    public Controller(IngredientRepo ingredientRepo) {
            this.repo = ingredientRepo;
    }
    
    @RequestMapping("/")
    public String index() {
	return "c'est la banana, mjam, mjam";
    }
    
    @GetMapping(value = "/get/ingredient")
    public List<Ingredient> geefAlleIngredienten() {
        return repo.geefAlleIngredienten();
    }
    
    @PostMapping(value = "/post/ingredient")
    public ResponseEntity<Ingredient> plaatsIngredient(@RequestBody Ingredient ingredient) {
            HttpStatus status = repo.postNieuwIngredient(ingredient);
            return new ResponseEntity<Ingredient>(ingredient, status);
    }
    
    // in repo
//    public HttpStatus postNieuweGebruiker(Gebruiker gebruiker) {
//        jdbcTemplate.execute("insert into GEBRUIKER (naam, achternaam) values ('"
//                        + gebruiker.getNaam() + "', '" + gebruiker.getAchternaam() + "')");
//        logger.info(gebruiker + "aangemaakt");
//        return HttpStatus.CREATED;
//}
    
//    @PostMapping("/post/ingredient")
//    public void plaatsIngredient(HttpServletRequest request) {
//        Ingredient ingredient = new Ingredient();
//        System.out.println(request.getAttribute("naam"));
//        System.out.println(request.getAttribute("banaan"));
//    }
    
}