package co.japo.doityourself.services;

import co.japo.doityourself.commands.IngredientCommand;
import co.japo.doityourself.converters.IngredientToIngredientCommand;
import co.japo.doityourself.domain.Ingredient;
import co.japo.doityourself.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;
    private IngredientToIngredientCommand ingredientCommandConverter;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientToIngredientCommand converter){
        this.ingredientRepository = ingredientRepository;
        this.ingredientCommandConverter = converter;
    }

    @Override
    public List<IngredientCommand> findByRecipeId(Long recipeId) {
        List<Ingredient> ingredients = ingredientRepository.findByRecipeId(recipeId);
        List<IngredientCommand> result = new ArrayList<>();
        ingredients.stream()
                .map(i -> ingredientCommandConverter.convert(i))
                .forEach(result::add);
        return result;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findByRecipeIdAndId(recipeId,ingredientId).get();
        if(ingredient == null){
            throw new RuntimeException("Ingredient for recipe id "+recipeId+" with the id "+ingredientId
            +"doesn't found!");
        }
        return ingredientCommandConverter.convert(ingredient);
    }

}
