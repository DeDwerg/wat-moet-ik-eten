package watmoetiketen.model;

import watmoetiketen.dao.Ingredient;

import java.util.List;

public class Gerecht {

    private Integer id;
    private String naam;
    private Boolean vis;
    private Boolean vlees;
    private Integer aantalPersonen;
    private Integer gebruikerId;
    private List<Ingredient> ingredienten;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Boolean getVis() {
        return vis;
    }

    public void setVis(Boolean vis) {
        this.vis = vis;
    }

    public Boolean getVlees() {
        return vlees;
    }

    public void setVlees(Boolean vlees) {
        this.vlees = vlees;
    }

    public Integer getAantalPersonen() {
        return aantalPersonen;
    }

    public void setAantalPersonen(Integer aantalPersonen) {
        this.aantalPersonen = aantalPersonen;
    }

    public Integer getGebruikerId() {
        return gebruikerId;
    }

    public void setGebruikerId(Integer gebruikerId) {
        this.gebruikerId = gebruikerId;
    }

    public List<Ingredient> getIngredienten() {
        return this.ingredienten;
    }

    public void setIngredienten(List<Ingredient> ingredienten) {
        this.ingredienten = ingredienten;
    }
}