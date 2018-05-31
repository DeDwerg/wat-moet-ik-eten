package watmoetiketen.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import watmoetiketen.dao.Gebruiker;
import watmoetiketen.dao.GebruikerRepository;

@Slf4j
@Service
public class GebruikerRepo {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    public HttpStatus maakNieuweGebruiker(Gebruiker gebruiker) {
        HttpStatus httpStatus;
        Optional<Gebruiker> optionalGebruiker = gebruikerRepository.getGebruiker(gebruiker.getNaam(),
                gebruiker.getWachtwoord());
        if (optionalGebruiker.isPresent()) {
            httpStatus = HttpStatus.CONFLICT;
        } else {
            gebruikerRepository.saveAndFlush(gebruiker);
            httpStatus = HttpStatus.CREATED;
        }
        return httpStatus;
    }

    public ResponseEntity loginGebruiker(Gebruiker gebruiker) {
        ResponseEntity entity;
        Optional<Gebruiker> optionalGebruiker = gebruikerRepository.getGebruiker(gebruiker.getNaam(),
                gebruiker.getWachtwoord());
        if (optionalGebruiker.isPresent()) {
            entity = new ResponseEntity(optionalGebruiker.get(), HttpStatus.OK);
        } else {
            entity = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return entity;
    }
}
