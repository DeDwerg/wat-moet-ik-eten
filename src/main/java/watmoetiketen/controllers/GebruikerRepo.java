package watmoetiketen.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import utils.ExcelWriter;
import watmoetiketen.Gebruiker;

@Slf4j
@Repository
public class GebruikerRepo {
	
	public HttpStatus maakNieuweGebruiker(Gebruiker gebruiker) {
		
		ExcelWriter excelWriter = new ExcelWriter();
		return excelWriter.maakNieuweGebruiker(gebruiker);
	}
	
	public HttpStatus loginGebruiker(Gebruiker gebruiker) {
		ExcelWriter excelWriter = new ExcelWriter();
		return excelWriter.loginGebruiker(gebruiker);
	}

}
