package watmoetiketen.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{

    @Query("FROM Ingredient where gerechtId =?1")
    Optional<List<Ingredient>> getIngredienten(Integer gerechtId);

}
