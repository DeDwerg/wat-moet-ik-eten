package watmoetiketen.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "INGREDIENT")
@Data
public class Ingredient {
    
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

}
