package co.japo.doityourself.controllers;

import co.japo.doityourself.commands.RecipeCommand;
import co.japo.doityourself.domain.Recipe;
import co.japo.doityourself.services.DataConverterService;
import co.japo.doityourself.services.MathService;
import co.japo.doityourself.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private MockMvc mockMvc;

    private final long RECIPE_ID = 3l;
    private final String RECIPE_DESCRIPTION = "Some text";

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        recipeController =  new RecipeController(recipeService,mathService,dataConverterService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }


    @Test
    public void listRecipes() throws Exception{
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

        MockMvc mock = MockMvcBuilders.standaloneSetup(recipeController).build();
        mock.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/list"));
    }

    @Test
    public void showRecipeById() throws Exception{
        //given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);

        //when
        when(recipeService.getById(anyLong())).thenReturn(recipe);

        //then
        assertEquals("recipe/show",recipeController.showRecipeById(RECIPE_ID+"",model));
        verify(recipeService,times(1)).getById(anyLong());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void createNewRecipe() throws Exception{
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/save"));
    }

    @Test
    public void updateRecipe() throws Exception{
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1l);

        when(recipeService.getCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/save"));
    }

    @Test
    public void saveOrUpdateRecipe() throws Exception{
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setDescription(RECIPE_DESCRIPTION);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id","")
                        .param("description",RECIPE_DESCRIPTION)
                ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/"+RECIPE_ID+"/show"));
    }
}