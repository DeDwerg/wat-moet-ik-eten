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

    @PostMapping(value = "/post/gebruiker")
    public ResponseEntity postGebruiker(@RequestBody Gebruiker gebruiker) {
        HttpStatus status = gebruikerRepo.maakNieuweGebruiker(gebruiker);
        return new ResponseEntity(status);
    }

    @PostMapping(value = "/login/gebruiker")
    public ResponseEntity login(@RequestBody Gebruiker gebruiker) {
        HttpStatus status = gebruikerRepo.loginGebruiker(gebruiker);
        log.info("gereturneerde status " + status.value());
        return new ResponseEntity("{\"statuscode\": " + status.value() + "}", status);
    }

    @PostMapping(value = "/post/gerecht")
    public ResponseEntity<Gerecht> postGerecht(@RequestBody Gerecht gerecht) {
        HttpStatus status = gerechtRepo.postNieuwGerecht(gerecht);
        return new ResponseEntity<Gerecht>(gerecht, status);
    }

    @PostMapping(value = "/get/random/gerecht")
    public ResponseEntity zoekRandomGerecht(@RequestBody Gebruiker gebruiker) {
        return gerechtRepo.zoekRandomGerecht(gebruiker);
    }

    @PostMapping(value = "/verwijder/gerecht/{gebruikerId}")
    public ResponseEntity verwijderGerecht(@PathVariable int gebruikerId, @RequestBody Gerecht gerecht) {
        ResponseEntity response = gerechtRepo.verwijderGerecht(gebruikerId, gerecht);
        return response;
    }

    @PostMapping(value = "/get/alle/gerechten")
    public ResponseEntity getAlleGerechten(@RequestBody Gebruiker gebruiker) {
        log.info("gerechten worden opgehaald voor " + gebruiker.getNaam());
        return gerechtRepo.getAlleGerechten(gebruiker);
    }

}