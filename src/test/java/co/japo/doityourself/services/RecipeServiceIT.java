package co.japo.doityourself.services;

import co.japo.doityourself.commands.RecipeCommand;
import co.japo.doityourself.converters.*;
import co.japo.doityourself.domain.Recipe;
import co.japo.doityourself.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    @Autowired
    private RecipeServiceImpl recipeService;

    private final long RECIPE_ID = 3l;
    private final String RECIPE_DESCRIPTION = "Some text";


    @Test
    public void saveRecipeCommand(){
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setDescription(RECIPE_DESCRIPTION);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setDescription(RECIPE_DESCRIPTION);

        RecipeCommand saved = recipeService.saveRecipeCommand(recipeCommand);

        assertNotNull(saved);
        assertEquals(RECIPE_ID,saved.getId().longValue());
        assertEquals(RECIPE_DESCRIPTION,saved.getDescription());
    }
}
