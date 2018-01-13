package watmoetiketen;

public class Gebruiker {

	private int id;
	private String naam;
	private String wachtwoord;
	
    public Gebruiker () {
        
    }
    
    public Gebruiker(int id, String naam, String wachtwoord) {
    	this.id = id;
    	this.naam = naam;
    	this.wachtwoord = wachtwoord;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
	
}
