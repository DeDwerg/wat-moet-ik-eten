package watmoetiketen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import watmoetiketen.Ingredient;

@Repository
public class IngredientRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public HttpStatus postNieuwIngredient(Ingredient ingredient) {
        jdbcTemplate.execute("insert into INGREDIENT (naam, eenheid, hoeveelheid) values ('"
                + ingredient.getNaam() + "', '" + ingredient.getEenheid() + "', '" + ingredient.getHoeveelheid() + "')");
        return HttpStatus.CREATED;
    }

    public List<Ingredient> geefAlleIngredienten() {
        return jdbcTemplate.query("select naam, hoeveelheid, eenheid from INGREDIENT",
                (rs, rowNum) -> new Ingredient(rs.getString("naam"), rs.getInt("hoeveelheid"), rs.getString("eenheid"), rs.getInt("gerechtid")));
    }
    
//    return jdbcTemplate.query("select id, naam, achternaam from GEBRUIKER",
//            (rs, rowNum) -> new Gebruiker(rs.getInt("id"), rs.getString("naam"), rs.getString("achternaam")));

}
