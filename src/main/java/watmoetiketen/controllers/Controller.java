package watmoetiketen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import watmoetiketen.dao.Gebruiker;
import watmoetiketen.dao.Gerecht;

@Slf4j
@CrossOrigin
@RestController
public class Controller {

    private GerechtRepo gerechtRepo;
    private GebruikerRepo gebruikerRepo;

    @Autowired
    public Controller(GerechtRepo gerechtRepo, GebruikerRepo gebruikerRepo) {
        this.gerechtRepo = gerechtRepo;
        this.gebruikerRepo = gebruikerRepo;
    }

    @RequestMapping("/")
    public String index() {
        return "Dit is de default page waar niemand zou moeten kunnen komen.";
    }

    //v
    @PostMapping(value = "/post/gebruiker")
    public ResponseEntity postGebruiker(@RequestBody Gebruiker gebruiker) {
        ResponseEntity entity = gebruikerRepo.maakNieuweGebruiker(gebruiker);
        System.out.println("gebruiker aanmaken " + gebruiker.getNaam() + " " + entity.getStatusCode());
        // retourneer ook gebruiker
        return entity;
    }

    
    @PostMapping(value = "/login/gebruiker")
    public ResponseEntity login(@RequestBody Gebruiker gebruiker) {
        System.out.println("gebruiker inloggen " + gebruiker.getNaam());
        // retourneer ook gebruiker
        return gebruikerRepo.loginGebruiker(gebruiker);
    }

    @PostMapping(value = "/post/gerecht")
    public ResponseEntity<Gerecht> postGerecht(@RequestBody Gerecht gerecht) {
        HttpStatus status = gerechtRepo.postNieuwGerecht(gerecht);
        System.out.println("gerecht aanmaken " + gerecht.getNaam() + " " + status);
        return new ResponseEntity(gerecht, status);
    }

    @PostMapping(value = "/get/random/gerecht")
    public ResponseEntity zoekRandomGerecht(@RequestBody Gebruiker gebruiker) {
        System.out.println("gerecht zoeken " + gebruiker.getNaam());
        return gerechtRepo.zoekRandomGerecht(gebruiker);
    }

    @PostMapping(value = "/verwijder/gerecht/{gebruikerId}")
    public ResponseEntity verwijderGerecht(@PathVariable int gebruikerId, @RequestBody Gerecht gerecht) {
        ResponseEntity response = gerechtRepo.verwijderGerecht(gebruikerId, gerecht);
        System.out.println("verwijder gerecht " + gerecht.getNaam() + response.getStatusCode());
        return response;
    }

    @PostMapping(value = "/get/alle/gerechten")
    public ResponseEntity getAlleGerechten(@RequestBody Gebruiker gebruiker) {
        System.out.println("gerechten worden opgehaald voor " + gebruiker.getNaam());
        return gerechtRepo.getAlleGerechten(gebruiker);
    }

}