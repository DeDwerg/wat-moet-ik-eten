package watmoetiketen.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "INGREDIENT")
@Data
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

}
