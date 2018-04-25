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
@Table(name = "GERECHT", schema="GERECHT")
@Data
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
    
//    private List<Ingredient> ingredient;

}
