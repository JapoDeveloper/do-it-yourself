package co.japo.doityourself.repositories;

import co.japo.doityourself.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe,Long> {

}
