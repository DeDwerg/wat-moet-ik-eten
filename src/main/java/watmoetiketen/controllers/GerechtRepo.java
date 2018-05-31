package watmoetiketen.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import watmoetiketen.dao.Gebruiker;
import watmoetiketen.dao.Gerecht;
import watmoetiketen.dao.GerechtRepository;
import watmoetiketen.dao.Ingredient;
import watmoetiketen.dao.IngredientRepository;

@Service
public class GerechtRepo {

    @Autowired
    private GerechtRepository gerechtRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public HttpStatus postNieuwGerecht(Gerecht gerecht) {
        // iets met ingredienten gaan doen dat die toegevoegd worden (mapping in gerecht klas gaat niet)
        gerechtRepository.saveAndFlush(gerecht);
//        for (Ingredient ingredient : optionalIngredienten.get())
//            ingredientRepository.saveAndFlush(ingredient);
        return HttpStatus.CREATED;
    }
    
//    public HttpStatus maakNieuweGebruiker(Gebruiker gebruiker) {
//        HttpStatus httpStatus;
//        Optional<Gebruiker> optionalGebruiker = gebruikerRepository.getGebruiker(gebruiker.getNaam(),
//                gebruiker.getWachtwoord());
//        if (optionalGebruiker.isPresent()) {
//            httpStatus = HttpStatus.CONFLICT;
//        } else {
//            gebruikerRepository.saveAndFlush(gebruiker);
//            httpStatus = HttpStatus.CREATED;
//        }
//        return httpStatus;
//    }

    public ResponseEntity verwijderGerecht(int gebruikerId, Gerecht gerecht) {

        HttpStatus httpStatus;

        Optional<Gerecht> optionalGerecht = gerechtRepository.getGerecht(gerecht.getNaam(), gebruikerId);
        Optional<Ingredient[]> optionalIngredienten = ingredientRepository.getIngredienten(gerecht.getId());
        if (optionalGerecht.isPresent()) {
            if (optionalIngredienten.isPresent()) {
                for (Ingredient ingredient : optionalIngredienten.get()) {
                    ingredientRepository.delete(ingredient);
                }
                gerechtRepository.delete(gerecht);
                httpStatus = HttpStatus.OK;
            } else {
                httpStatus = HttpStatus.NOT_FOUND;
            }

        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity(httpStatus);
    }

    public ResponseEntity zoekRandomGerecht(Gebruiker gebruiker) {

        List<Gerecht> gerechten = new ArrayList<>();

        Optional<Gerecht[]> optionalGerechten = gerechtRepository.getGerechten(gebruiker.getId());
        if (optionalGerechten.isPresent()) {
            for (Gerecht gerecht : optionalGerechten.get()) {
                gerechten.add(gerecht);
            }
            int randomGetal = (int) (Math.random() * gerechten.size() + 1);
            Gerecht gerecht = gerechten.get(randomGetal - 1);
            return new ResponseEntity(gerecht, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    public ResponseEntity getAlleGerechten(Gebruiker gebruiker) {
        List<Gerecht> gerechten = new ArrayList<>();
        Optional<Gerecht[]> optionalGerechten = gerechtRepository.getGerechten(gebruiker.getId());
        if (optionalGerechten.isPresent()) {
            return new ResponseEntity(optionalGerechten.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
