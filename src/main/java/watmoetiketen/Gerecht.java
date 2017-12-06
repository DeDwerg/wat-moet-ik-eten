package watmoetiketen;

import java.util.ArrayList;

public class Gerecht {

    private boolean vegetarisch;
    private boolean vis;
    private boolean vlees;
    private String naam;
    private int aantalPersonen;
    private ArrayList<Ingredient> ingredienten;
    private String eenheid;
    
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
    public String getEenheid() {
        return eenheid;
    }
    public void setEenheid(String eenheid) {
        this.eenheid = eenheid;
    }
}
