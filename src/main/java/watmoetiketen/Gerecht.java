package watmoetiketen;

import java.util.ArrayList;

public class Gerecht {

//	excelvolgorde: id naam	vis	vlees	aantalpersonen	gebruikerId
	
    private boolean vegetarisch;
    private boolean vis;
    private boolean vlees;
    private String naam;
    private int aantalPersonen;
    private ArrayList<Ingredient> ingredienten;
    private int gebruikerId;
    private int id;
    
    public Gerecht(String naam, boolean vis, boolean vlees, int aantalPersonen, int gebruikerId) {
    	this.naam = naam;
    	this.vis = vis;
    	this. vlees = vlees;
    	this.aantalPersonen = aantalPersonen;
    	this.gebruikerId = gebruikerId;
    }
    
    public Gerecht(){}
    
    public void setId(int id) {
    	this.id = id;
    }
    public int getId() {
    	return this.id;
    }
    public boolean isVegetarisch() {
        return vegetarisch;
    }
    public void setVegetarisch(boolean vegetarisch) {
        this.vegetarisch = vegetarisch;
    }
    public boolean isVis() {
        return vis;
    }
    public void setVis(boolean vis) {
        this.vis = vis;
    }
    public boolean isVlees() {
        return vlees;
    }
    public void setVlees(boolean vlees) {
        this.vlees = vlees;
    }
    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public int getAantalPersonen() {
        return aantalPersonen;
    }
    public void setAantalPersonen(int aantalPersonen) {
        this.aantalPersonen = aantalPersonen;
    }
    public ArrayList<Ingredient> getIngredienten() {
        return ingredienten;
    }
    public void setIngredienten(ArrayList<Ingredient> ingredienten) {
        this.ingredienten = ingredienten;
    }
    public void setGebruikerId(int gebruikerId) {
    	this.gebruikerId = gebruikerId;
    }
    public int getGebruikerId() {
    	return gebruikerId;
    }

}
