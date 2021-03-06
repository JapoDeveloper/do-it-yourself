package co.japo.doityourself.controllers;

import co.japo.doityourself.domain.Recipe;
import co.japo.doityourself.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerTest {

    private RecipeController recipeController;
    @Mock
    private RecipeService recipeService;
    @Mock
    private Model model;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        recipeController =  new RecipeController(recipeService);
    }

    @Test
    public void listRecipesMvc() throws Exception{
        MockMvc mock = MockMvcBuilders.standaloneSetup(recipeController).build();
        mock.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/list"));
    }

    @Test
    public void listRecipes(){
        //given
        final List<Recipe> RECIPES = new ArrayList<Recipe>(){
            {
                add(new Recipe());
            }
        };
        ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);

        //when
        when(recipeService.list()).thenReturn(RECIPES);

        //then
        assertEquals("recipe/list",recipeController.listRecipes(model));
        verify(recipeService,times(1)).list();
        verify(model,times(1)).addAttribute(eq("allRecipes"),listCaptor.capture());
        assertEquals(RECIPES.size(),listCaptor.getValue().size());
    }
}
