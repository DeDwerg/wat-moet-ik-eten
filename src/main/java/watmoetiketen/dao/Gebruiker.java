package watmoetiketen.dao;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name="GEBRUIKER")
@Data
public class Gebruiker {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "GEBRUIKER_SEQ", allocationSize = 1)
    @Column(name="ID")
    private Integer id;
    
    @Column(name = "NAAM")
    private String naam;
    
    @Column(name = "WACHTWOORD")
    private String wachtwoord;

}
