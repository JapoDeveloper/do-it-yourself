package co.japo.doityourself.services;

import co.japo.doityourself.commands.RecipeCommand;
import co.japo.doityourself.converters.*;
import co.japo.doityourself.domain.Recipe;
import co.japo.doityourself.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecipeServiceTest {

    private RecipeServiceImpl recipeService;
    @Mock
    private RecipeRepository recipeRepository;

    private final long RECIPE_ID = 1l;
    private final String RECIPE_DESCRIPTION = "Some text";

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository,
                new RecipeToRecipeCommand(new CategoryToCategoryCommand(),new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                        new NotesToNotesCommand()),
                new RecipeCommandToRecipe(new CategoryCommandToCategory(), new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                        new NotesCommandToNotes()));
    }

    @Test
    public void list(){
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe());

        when(recipeService.list()).thenReturn(recipes);

        assertEquals(1l,recipeService.list().size());
        verify(recipeRepository,times(1)).findAll();
    }

    @Test
    public void getById(){
        Optional<Recipe> optionalRecipe = Optional.of(new Recipe(RECIPE_ID));
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        Recipe result = recipeService.getById(RECIPE_ID);
        assertNotNull(result);
        assertEquals(RECIPE_ID,result.getId().longValue());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }

    @Test
    public void getCommandById(){
        Optional<Recipe> optionalRecipe = Optional.of(new Recipe(RECIPE_ID));
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        RecipeCommand result = recipeService.getCommandById(RECIPE_ID);
        assertNotNull(result);
        assertEquals(RECIPE_ID,result.getId().longValue());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }

    @Test
    public void deleteById(){
        long recipeId = 1l;
        recipeService.deleteById(recipeId);
        verify(recipeRepository,times(1)).deleteById(anyLong());
    }

}
