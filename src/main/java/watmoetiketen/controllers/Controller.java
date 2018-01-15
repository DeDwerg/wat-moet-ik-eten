package watmoetiketen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import watmoetiketen.Gebruiker;
import watmoetiketen.Gerecht;

//@Slf4j
@CrossOrigin
@RestController
public class Controller {

	private GerechtRepo gerechtRepo;
	private GebruikerRepo gebruikerRepo;

	@Autowired
	public Controller(
			GerechtRepo gerechtRepo,
			GebruikerRepo gebruikerRepo
			) {
		this.gerechtRepo = gerechtRepo;
		this.gebruikerRepo = gebruikerRepo;
	}

	@RequestMapping("/")
	public String index() {
		return "c'est la banana, mjam, mjam";
	}

	// werkt goed
	@PostMapping(value = "/post/gebruiker")
	public ResponseEntity postGebruiker(@RequestBody Gebruiker gebruiker) {
		HttpStatus status = gebruikerRepo.maakNieuweGebruiker(gebruiker);
		return new ResponseEntity(status);
	}
	
	// werkt goed
	@PostMapping(value = "/login/gebruiker")
	public ResponseEntity login(@RequestBody Gebruiker gebruiker) {
		HttpStatus status = gebruikerRepo.loginGebruiker(gebruiker);
		return new ResponseEntity(status);
	}
	
	// werkt goed
	@PostMapping(value = "/post/gerecht")
	public ResponseEntity<Gerecht> postGerecht(@RequestBody Gerecht gerecht) {
		HttpStatus status = gerechtRepo.postNieuwGerecht(gerecht);
		return new ResponseEntity<Gerecht>(gerecht, status);
	}
	
	// gerecht ophalen werkt. corresponderende ingredienten nog niet.
	@PostMapping(value = "/get/gerecht")
	public Gerecht zoekRandomGerecht(@RequestBody Gebruiker gebruiker) {
		return gerechtRepo.zoekRandomGerecht(gebruiker);
	}
	
	@PostMapping(value = "/verwijder/gerecht")
	public ResponseEntity verwijderGerecht(@RequestBody Gerecht gerecht) {
		gerechtRepo.verwijderGerecht(gerecht);
		return new ResponseEntity(HttpStatus.OK);
	}

}