package co.japo.doityourself.services;

import co.japo.doityourself.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RecipeService {

    List<Recipe> list();

}
