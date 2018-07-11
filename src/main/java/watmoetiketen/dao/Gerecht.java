package watmoetiketen.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "GERECHT", schema="EETDATA")
public class Gerecht {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "GERECHT_SEQ", allocationSize = 1)
    @Column(name="ID")
    private Integer id;
    
    @Column(name = "NAAM")
    private String naam;
    
    @Column(name = "VIS")
    private Boolean vis;
    
    @Column(name = "VLEES")
    private Boolean vlees;
    
    @Column(name = "AANTAL_PERSONEN")
    private Integer aantalPersonen;
    
    @Column(name = "GEBRUIKER_ID")
    private Integer gebruikerId;

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

    //    private List<Ingredient> ingredient;

}
