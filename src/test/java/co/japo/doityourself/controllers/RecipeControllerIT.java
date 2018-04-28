package co.japo.doityourself.controllers;

import co.japo.doityourself.domain.Recipe;
import co.japo.doityourself.services.DataConverterService;
import co.japo.doityourself.services.MathService;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RecipeControllerIT {

    private RecipeController recipeController;
    @Mock
    private RecipeService recipeService;
    @Mock
    private Model model;
    @Mock
    private MathService mathService;
    @Mock
    private DataConverterService dataConverterService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        recipeController =  new RecipeController(recipeService,mathService,dataConverterService);
    }

    @Test
    public void listRecipesMvc() throws Exception{
        MockMvc mock = MockMvcBuilders.standaloneSetup(recipeController).build();
        mock.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/list"));
    }

    @Test
    public void listRecipes(){
        //given
        List<Recipe> recipes = new ArrayList<Recipe>(){
            {
                add(new Recipe());
            }
        };
        ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);

        //when
        when(recipeService.list()).thenReturn(recipes);

        //then
        assertEquals("recipe/list",recipeController.listRecipes(model));
        verify(recipeService,times(1)).list();
        verify(model,times(1)).addAttribute(eq("allRecipes"),listCaptor.capture());
        assertEquals(recipes.size(),listCaptor.getValue().size());
    }

    @Test
    public void getRecipeById() throws Exception{
        //given
        Long recipeId = 1l;
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        //when
        when(recipeService.getById(recipeId)).thenReturn(Optional.of(recipe));

        //then
        assertEquals("recipe/show",recipeController.getRecipeById(recipeId.toString(),model));
        verify(recipeService,times(1)).getById(recipeId);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }
}


