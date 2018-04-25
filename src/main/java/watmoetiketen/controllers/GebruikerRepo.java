package watmoetiketen.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public HttpStatus loginGebruiker(Gebruiker gebruiker) {
        HttpStatus httpStatus;
        Optional<Gebruiker> optionalGebruiker = gebruikerRepository.getGebruiker(gebruiker.getNaam(),
                gebruiker.getWachtwoord());
        if (optionalGebruiker.isPresent()) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return httpStatus;
    }
}
