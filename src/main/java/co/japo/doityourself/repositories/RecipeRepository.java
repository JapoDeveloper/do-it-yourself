package co.japo.doityourself.repositories;

import co.japo.doityourself.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {

}
