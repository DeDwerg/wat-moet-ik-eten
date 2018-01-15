package watmoetiketen.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import utils.ExcelWriter;
import watmoetiketen.Gebruiker;
import watmoetiketen.Gerecht;
import watmoetiketen.Ingredient;

@Repository
public class GerechtRepo {
    
//    @Autowired
//    JdbcTemplate jdbcTemplate;

    public HttpStatus postNieuwGerecht(Gerecht gerecht) {
    	ExcelWriter excelWriter = new ExcelWriter();
    	excelWriter.maakNieuwGerecht(gerecht);
    	excelWriter.maakNieuwIngredient(gerecht.getIngredienten());
    	// nog iets met ingredienten aanmaken
//        jdbcTemplate.execute("insert into INGREDIENT (naam, eenheid, hoeveelheid) values ('"
//                + ingredient.getNaam() + "', '" + ingredient.getEenheid() + "', '" + ingredient.getHoeveelheid() + "')");
        return HttpStatus.CREATED;
    }
    
    public void verwijderGerecht(Gerecht gerecht) {
    	ExcelWriter excelWriter = new ExcelWriter();
    	// nog iets met ingredienten verwijderen
    	excelWriter.verwijderGerecht(gerecht);
    }
    
    public Gerecht zoekRandomGerecht(Gebruiker gebruiker) {
    	// nog iets met ingredienten ohalen
    	ExcelWriter excelWriter = new ExcelWriter();
    	Optional<Gerecht> optionalGerecht = excelWriter.zoekRandomGerecht(gebruiker);
    	if (optionalGerecht.isPresent()){
    		return optionalGerecht.get();
    	}
    	else {
    		// iets met not found
    		return null;
    	}
    }
    
//    	
//    	return ingredienten;
////        return jdbcTemplate.query("select naam, hoeveelheid, eenheid from INGREDIENT",
////                (rs, rowNum) -> new Ingredient(rs.getString("naam"), rs.getInt("hoeveelheid"), rs.getString("eenheid"), rs.getInt("gerechtid")));
//    }
    
//    return jdbcTemplate.query("select id, naam, achternaam from GEBRUIKER",
//            (rs, rowNum) -> new Gebruiker(rs.getInt("id"), rs.getString("naam"), rs.getString("achternaam")));

}
