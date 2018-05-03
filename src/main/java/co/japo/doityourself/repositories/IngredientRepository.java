package co.japo.doityourself.repositories;

import co.japo.doityourself.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient,Long> {

    List<Ingredient> findByRecipeId(Long recipeId);
    Optional<Ingredient> findByRecipeIdAndId(Long recipeId, Long ingredientId);

}
