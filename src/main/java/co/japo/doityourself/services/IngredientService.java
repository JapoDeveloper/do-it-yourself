package co.japo.doityourself.services;

import co.japo.doityourself.commands.IngredientCommand;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

    List<IngredientCommand> findByRecipeId(Long recipeId);
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteById(Long id);

}
