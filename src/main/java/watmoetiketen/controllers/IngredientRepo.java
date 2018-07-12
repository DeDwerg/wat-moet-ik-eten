package watmoetiketen.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import watmoetiketen.dao.Gerecht;
import watmoetiketen.dao.Ingredient;
import watmoetiketen.dao.IngredientRepository;

@Service
public class IngredientRepo {
    
    @Autowired
    private IngredientRepository ingredientRepository;

    public void maakNieuwIngredient(List<Ingredient> ingredienten) {
        for(Ingredient ingredient: ingredienten) {
            ingredientRepository.saveAndFlush(ingredient);
        }
    }

    public ResponseEntity geefIngredientenBijGerecht(Gerecht gerecht) {
        Optional<List<Ingredient>> optionalIngredienten = ingredientRepository.getIngredienten(gerecht.getId());
        if (optionalIngredienten.isPresent()) {
            return new ResponseEntity(optionalIngredienten.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
