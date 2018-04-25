package watmoetiketen.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GerechtRepository extends JpaRepository<Gerecht, Integer> {
    
    @Query("SELECT g FROM gerecht g where g =?1 AND g.gebruiker_id =?2")
    Optional<Gerecht> getGerecht(Gerecht gerecht, int gebruikerId);

    @Query("SELECT * FROM gerecht where gebruiker_id =?1")
    Optional<Gerecht[]> getGerechten(Integer gebruikerId);

}