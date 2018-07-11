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
@Table(name="GEBRUIKER", schema="EETDATA")
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

    public Integer getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
}
