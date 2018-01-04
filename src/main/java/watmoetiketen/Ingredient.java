package watmoetiketen;

import lombok.Data;

@Data
public class Ingredient {

    private String naam;
    private int hoeveelheid;
    private String eenheid;
    private int gerechtId;
    
    public Ingredient () {
        
    }
    
    public Ingredient(String naam, int hoeveelheid, String eenheid, int gerechtId) {
        this.naam = naam;
        this.hoeveelheid = hoeveelheid;
        this.eenheid = eenheid;
        this.setGerechtId(gerechtId);
    }
    
    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public int getHoeveelheid() {
        return hoeveelheid;
    }
    public void setHoeveelheid(int hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }
    public String getEenheid() {
        return eenheid;
    }
    public void setEenheid(String eenheid) {
        this.eenheid = eenheid;
    }

	public int getGerechtId() {
		return gerechtId;
	}

	public void setGerechtId(int gerechtId) {
		this.gerechtId = gerechtId;
	}
    
}
