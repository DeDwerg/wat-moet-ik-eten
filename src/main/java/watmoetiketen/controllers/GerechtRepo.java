package watmoetiketen.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import watmoetiketen.Gerecht;
import watmoetiketen.Ingredient;

@Repository
public class GerechtRepo {
    
//    @Autowired
//    JdbcTemplate jdbcTemplate;

    public HttpStatus postNieuwGerecht(Gerecht gerecht) {
    	System.out.println(gerecht);
    	System.out.println(gerecht.getNaam());
    	System.out.println(gerecht.getIngredienten());
//        jdbcTemplate.execute("insert into INGREDIENT (naam, eenheid, hoeveelheid) values ('"
//                + ingredient.getNaam() + "', '" + ingredient.getEenheid() + "', '" + ingredient.getHoeveelheid() + "')");
        return HttpStatus.CREATED;
    }
    
    public Gerecht geefGerecht() {
    	System.err.println("in geefgerecht");
    	Ingredient ingredient1 = new Ingredient();
    	ingredient1.setEenheid("mg");
    	ingredient1.setGerechtId(1);
    	ingredient1.setHoeveelheid(6);
    	ingredient1.setNaam("kaas");
    	
    	Ingredient ingredient2 = new Ingredient();
    	ingredient2.setEenheid("mg");
    	ingredient2.setGerechtId(1);
    	ingredient2.setHoeveelheid(6);
    	ingredient2.setNaam("worst");
    	
    	ArrayList <Ingredient> ingredienten = new ArrayList<>();
    	ingredienten.add(ingredient1);
    	ingredienten.add(ingredient2);
    	
    	Gerecht gerecht = new Gerecht();
    	gerecht.setAantalPersonen(1);
    	gerecht.setIngredienten(ingredienten);
    	gerecht.setNaam("kaasEnWorst");
    	gerecht.setVegetarisch(true);
    	gerecht.setVis(false);
    	gerecht.setVlees(false);
    	
    	return gerecht;
    }

//    public List<Ingredient> geefAlleIngredienten() {
//    	List<Ingredient> ingredienten = new ArrayList<>();
//    	Ingredient ingredient = new Ingredient();
//    	ingredient.setEenheid("mg");
//    	ingredient.setGerechtId(1);
//    	ingredient.setHoeveelheid(6);
//    	ingredient.setNaam("getTestIngredient");
//    	ingredienten.add(ingredient);
//    	
//    	return ingredienten;
////        return jdbcTemplate.query("select naam, hoeveelheid, eenheid from INGREDIENT",
////                (rs, rowNum) -> new Ingredient(rs.getString("naam"), rs.getInt("hoeveelheid"), rs.getString("eenheid"), rs.getInt("gerechtid")));
//    }
    
//    return jdbcTemplate.query("select id, naam, achternaam from GEBRUIKER",
//            (rs, rowNum) -> new Gebruiker(rs.getInt("id"), rs.getString("naam"), rs.getString("achternaam")));

}
