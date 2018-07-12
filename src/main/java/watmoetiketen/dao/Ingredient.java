package watmoetiketen.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "INGREDIENT", schema="EETDATA")
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "INGREDIENT_SEQ", allocationSize = 1)
    @Column(name="ID")
    private Integer id;
    
    @Column(name = "NAAM")
    private String naam;
    
    @Column(name = "HOEVEELHEID")
    private Integer hoeveelheid;
    
    @Column(name = "EENHEID")
    private String eenheid;
    
    @Column(name = "GERECHT_ID")
    private Integer gerechtId;

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

    public Integer getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(Integer hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }

    public String getEenheid() {
        return eenheid;
    }

    public void setEenheid(String eenheid) {
        this.eenheid = eenheid;
    }

    public Integer getGerechtId() {
        return gerechtId;
    }

    public void setGerechtId(Integer gerechtId) {
        this.gerechtId = gerechtId;
    }

    public Integer getGebruikerId() {
        return gebruikerId;
    }

    public void setGebruikerId(Integer gebruikerId) {
        this.gebruikerId = gebruikerId;
    }

}
