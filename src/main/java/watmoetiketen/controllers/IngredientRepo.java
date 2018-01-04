package watmoetiketen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import utils.ExcelWriter;
import watmoetiketen.Ingredient;

@Repository
public class IngredientRepo {
    
//    @Autowired
    

    public HttpStatus postNieuwIngredient(Ingredient ingredient) {
    	ExcelWriter excelWriter = new ExcelWriter();
    	excelWriter.kaas();
    	System.out.println(ingredient);
//        jdbcTemplate.execute("insert into INGREDIENT (naam, eenheid, hoeveelheid) values ('"
//                + ingredient.getNaam() + "', '" + ingredient.getEenheid() + "', '" + ingredient.getHoeveelheid() + "')");
        return HttpStatus.CREATED;
    }

    public Ingredient geefIngredient() {
    	Ingredient ingredient = new Ingredient();
    	ingredient.setEenheid("mg");
    	ingredient.setGerechtId(1);
    	ingredient.setHoeveelheid(6);
    	ingredient.setNaam("getTestIngredient");
    	
    	return ingredient;
//        return jdbcTemplate.query("select naam, hoeveelheid, eenheid from INGREDIENT",
//                (rs, rowNum) -> new Ingredient(rs.getString("naam"), rs.getInt("hoeveelheid"), rs.getString("eenheid"), rs.getInt("gerechtid")));
    }
    
//    return jdbcTemplate.query("select id, naam, achternaam from GEBRUIKER",
//            (rs, rowNum) -> new Gebruiker(rs.getInt("id"), rs.getString("naam"), rs.getString("achternaam")));

}
