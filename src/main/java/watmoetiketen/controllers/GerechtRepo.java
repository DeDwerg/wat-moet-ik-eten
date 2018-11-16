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

import javax.ws.rs.client.Entity;

@Service
public class GerechtRepo {

    @Autowired
    private GerechtRepository gerechtRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public HttpStatus postNieuwGerecht(watmoetiketen.model.Gerecht gerecht) {
        Gerecht daoGerecht = new Gerecht();
        daoGerecht.setAantalPersonen(gerecht.getAantalPersonen());
        daoGerecht.setGebruikerId(gerecht.getGebruikerId());
        daoGerecht.setId(gerecht.getId());
        daoGerecht.setNaam(gerecht.getNaam());
        daoGerecht.setVis(gerecht.getVis());
        daoGerecht.setVlees(gerecht.getVlees());
        int gerechtId = gerechtRepository.saveAndFlush(daoGerecht).getId();

        for (Ingredient ingredient : gerecht.getIngredienten()) {
            ingredient.setGerechtId(gerechtId);
            ingredientRepository.saveAndFlush(ingredient);
        }
        return HttpStatus.CREATED;
    }

    public ResponseEntity verwijderGerecht(int gerechtId) {

        HttpStatus httpStatus;
//
//        Optional<Gerecht> optionalGerecht = gerechtRepository.getGerecht(gerecht.getNaam(), gebruikerId);
//        Optional<List<Ingredient>> optionalIngredienten = ingredientRepository.getIngredienten(gerecht.getId());
//        if (optionalGerecht.isPresent()) {
//            if (optionalIngredienten.isPresent()) {
//                for (Ingredient ingredient : optionalIngredienten.get()) {
//                    ingredientRepository.delete(ingredient);
//                }
//                gerechtRepository.delete(gerecht);
                httpStatus = HttpStatus.OK;
//            } else {
//                httpStatus = HttpStatus.NOT_FOUND;
//            }
//
//        } else {
//            httpStatus = HttpStatus.NOT_FOUND;
//        }
        return new ResponseEntity(httpStatus);
    }

    public ResponseEntity zoekRandomGerecht(Gebruiker gebruiker) {

      Optional<List<Gerecht>> optionalGerechten = gerechtRepository.getGerechten(gebruiker.getId());
      if (optionalGerechten.isPresent()) {
        List<Gerecht> daoGerechten = optionalGerechten.get();
        List<watmoetiketen.model.Gerecht> gerechten = new ArrayList<>();
        for (Gerecht daoGerecht : daoGerechten) {
          Optional<List<Ingredient>> optionalIngredienten = ingredientRepository.getIngredienten(daoGerecht.getId());
          if (optionalIngredienten.isPresent()) {
            watmoetiketen.model.Gerecht gerecht = new watmoetiketen.model.Gerecht();
            gerecht.setAantalPersonen(daoGerecht.getAantalPersonen());
            gerecht.setGebruikerId(daoGerecht.getGebruikerId());
            gerecht.setId(daoGerecht.getId());
            gerecht.setNaam(daoGerecht.getNaam());
            gerecht.setVis(daoGerecht.getVis());
            gerecht.setVlees(daoGerecht.getVlees());
            gerecht.setIngredienten(new ArrayList<>(optionalIngredienten.get()));
            gerechten.add(gerecht);
          }
        }

            int randomGetal = (int) (Math.random() * gerechten.size() + 1);
            watmoetiketen.model.Gerecht gerecht = gerechten.get(randomGetal - 1);
            return new ResponseEntity(gerecht, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getAlleGerechten(Gebruiker gebruiker) {
        Optional<List<Gerecht>> optionalGerechten = gerechtRepository.getGerechten(gebruiker.getId());
        if (optionalGerechten.isPresent()) {
            List<Gerecht> daoGerechten = optionalGerechten.get();
            List<watmoetiketen.model.Gerecht> gerechten = new ArrayList<>();
            for(Gerecht daoGerecht : daoGerechten) {
                Optional<List<Ingredient>> optionalIngredienten = ingredientRepository.getIngredienten(daoGerecht.getId());
                if(optionalIngredienten.isPresent()) {
                    watmoetiketen.model.Gerecht gerecht = new watmoetiketen.model.Gerecht();
                    gerecht.setAantalPersonen(daoGerecht.getAantalPersonen());
                    gerecht.setGebruikerId(daoGerecht.getGebruikerId());
                    gerecht.setId(daoGerecht.getId());
                    gerecht.setNaam(daoGerecht.getNaam());
                    gerecht.setVis(daoGerecht.getVis());
                    gerecht.setVlees(daoGerecht.getVlees());
                    gerecht.setIngredienten(new ArrayList<>(optionalIngredienten.get()));
                    gerechten.add(gerecht);
                }
            }
            return new ResponseEntity(gerechten, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
