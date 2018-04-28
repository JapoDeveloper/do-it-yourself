package co.japo.doityourself.services;

import co.japo.doityourself.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    List<Recipe> list();
    Optional<Recipe> getById(Long id);

}
