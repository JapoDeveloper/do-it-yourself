package co.japo.doityourself.services;

import co.japo.doityourself.commands.RecipeCommand;
import co.japo.doityourself.converters.RecipeCommandToRecipe;
import co.japo.doityourself.converters.RecipeToRecipeCommand;
import co.japo.doityourself.domain.Recipe;
import co.japo.doityourself.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;
    private RecipeToRecipeCommand recipeCommandConverter;
    private RecipeCommandToRecipe recipeConverter;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeCommandConverter, RecipeCommandToRecipe recipeConverter) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandConverter = recipeCommandConverter;
        this.recipeConverter = recipeConverter;
    }

    @Override
    public List<Recipe> list(){
        List<Recipe> recipes = new ArrayList<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    @Override
    public Recipe getById(Long id){
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(!recipe.isPresent()){
            throw new RuntimeException("Recipe with id "+id+" doesn't found!");
        }
        return recipe.get();
    }

    @Override
    @Transactional
    public RecipeCommand getCommandById(Long id) {
        return recipeCommandConverter.convert(getById(id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe recipe = recipeConverter.convert(recipeCommand);
        recipe = recipeRepository.save(recipe);
        log.debug("Recipe with id "+recipe.getId()+" saved!");
        return recipeCommandConverter.convert(recipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
