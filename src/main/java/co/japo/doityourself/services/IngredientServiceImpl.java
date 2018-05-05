package co.japo.doityourself.services;

import co.japo.doityourself.commands.IngredientCommand;
import co.japo.doityourself.converters.IngredientCommandToIngredient;
import co.japo.doityourself.converters.IngredientToIngredientCommand;
import co.japo.doityourself.domain.Ingredient;
import co.japo.doityourself.repositories.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;
    private IngredientToIngredientCommand ingredientCommandConverter;
    private IngredientCommandToIngredient ingredientConverter;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientToIngredientCommand converter,
                                 IngredientCommandToIngredient ingredientConverter){
        this.ingredientRepository = ingredientRepository;
        this.ingredientCommandConverter = converter;
        this.ingredientConverter = ingredientConverter;
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
        Optional<Ingredient> ingredient = ingredientRepository.findByRecipeIdAndId(recipeId,ingredientId);
        if(!ingredient.isPresent()){
            throw new RuntimeException("Ingredient for recipe id "+recipeId+" with the id "+ingredientId
            +"doesn't found!");
        }
        return ingredientCommandConverter.convert(ingredient.get());
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Ingredient saved = ingredientRepository.save(ingredientConverter.convert(ingredientCommand));
        log.debug("Ingredient with id "+saved.getId()+" was saved!");
        return ingredientCommandConverter.convert(saved);
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        ingredientRepository.deleteById(id);
        log.debug("Ingredient with id "+id+" was deleted!");
    }
}
