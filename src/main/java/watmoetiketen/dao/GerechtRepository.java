package watmoetiketen.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GerechtRepository extends JpaRepository<Gerecht, Integer> {
    
    @Query("SELECT g FROM Gerecht g where g.naam =?1 AND g.gebruikerId =?2")
    Optional<Gerecht> getGerecht(String gerechtNaam, int gebruikerId);

    @Query("FROM Gerecht where gebruikerId =?1")
    Optional<Gerecht[]> getGerechten(Integer gebruikerId);

}