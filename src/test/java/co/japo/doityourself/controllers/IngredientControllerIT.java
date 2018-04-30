package co.japo.doityourself.controllers;

import co.japo.doityourself.commands.RecipeCommand;
import co.japo.doityourself.services.MathService;
import co.japo.doityourself.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IngredientControllerIT {

    private IngredientController ingredientController;
    @Mock
    private RecipeService recipeService;
    @Mock
    private MathService mathService;
    MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService,mathService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void displayListOfIngredients() throws Exception{
        long recipeId = 1l;
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipeId);

        when(recipeService.getCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/"+recipeId+"/ingredients"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/ingredient/list"));

        verify(recipeService,times(1)).getCommandById(anyLong());
    }
}
