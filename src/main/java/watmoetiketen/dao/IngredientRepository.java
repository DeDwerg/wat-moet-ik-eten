package watmoetiketen.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{

    @Query("SELECT * FROM ingredient where gerecht_id =?1")
    Optional<Ingredient[]> getIngredienten(Integer gerechtId);

}
