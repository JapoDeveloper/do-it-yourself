package co.japo.doityourself.services;

import co.japo.doityourself.domain.Recipe;
import co.japo.doityourself.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class RecipeServiceTest {

    private RecipeServiceImpl recipeService;
    @Mock
    private RecipeRepository recipeRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void list(){
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe());

        when(recipeService.list()).thenReturn(recipes);

        assertEquals(1l,recipeService.list().size());
        verify(recipeRepository,times(1)).findAll();
    }
}
