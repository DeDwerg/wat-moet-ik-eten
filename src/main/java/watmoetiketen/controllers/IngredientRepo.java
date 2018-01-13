package watmoetiketen.controllers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import utils.ExcelWriter;
import watmoetiketen.Gerecht;
import watmoetiketen.Ingredient;

@Repository
public class IngredientRepo {
    
//    @Autowired
    

    public void maakNieuwIngredient(List<Ingredient> ingredienten) {
    	ExcelWriter excelWriter = new ExcelWriter();
    	excelWriter.maakNieuwIngredient(ingredienten);
    	

//        jdbcTemplate.execute("insert into INGREDIENT (naam, eenheid, hoeveelheid) values ('"
//                + ingredient.getNaam() + "', '" + ingredient.getEenheid() + "', '" + ingredient.getHoeveelheid() + "')");
    }

    public List<Ingredient> geefIngredientenBijGerecht(Gerecht gerecht) {
    	ExcelWriter excelWriter = new ExcelWriter();
    	return excelWriter.geeftIngredientenBijGerecht(gerecht);
//        return jdbcTemplate.query("select naam, hoeveelheid, eenheid from INGREDIENT",
//                (rs, rowNum) -> new Ingredient(rs.getString("naam"), rs.getInt("hoeveelheid"), rs.getString("eenheid"), rs.getInt("gerechtid")));
    }
    
    public void verwijderIngredient(Ingredient ingredient) {
    	ExcelWriter excelWriter = new ExcelWriter();
    	excelWriter.verwijderIngredient(ingredient);
    }
   
//    return jdbcTemplate.query("select id, naam, achternaam from GEBRUIKER",
//            (rs, rowNum) -> new Gebruiker(rs.getInt("id"), rs.getString("naam"), rs.getString("achternaam")));

}
