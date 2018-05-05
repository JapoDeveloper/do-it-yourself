package co.japo.doityourself.controllers;

import co.japo.doityourself.commands.IngredientCommand;
import co.japo.doityourself.commands.RecipeCommand;
import co.japo.doityourself.services.IngredientService;
import co.japo.doityourself.services.MathService;
import co.japo.doityourself.services.RecipeService;
import co.japo.doityourself.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IngredientControllerIT {

    private IngredientController ingredientController;
    @Mock
    private RecipeService recipeService;
    @Mock
    private IngredientService ingredientService;
    @Mock
    private UnitOfMeasureService uomService;
    @Mock
    private MathService mathService;
    MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService,ingredientService,uomService,mathService);
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

    @Test
    public void displayIngredientDetails() throws Exception{
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1l);
        ingredientCommand.setRecipeId(1l);

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong())).thenReturn(ingredientCommand);

        mockMvc.perform(get("/recipe/1/ingredients/1/show"))
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(status().isOk());
    }

    @Test
    public void createNewIngredient() throws Exception{
        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("listOfUoms"))
                .andExpect(view().name("recipe/ingredient/save"));
    }

    @Test
    public void updateIngredient() throws Exception{
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1l);

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong())).thenReturn(ingredientCommand);

        mockMvc.perform(get("/recipe/1/ingredients/1/update"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("listOfUoms"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/save"));
    }


    @Test
    public void deleteIngredient() throws Exception{
        mockMvc.perform(get("/recipe/1/ingredients/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredients"));
        verify(ingredientService,times(1)).deleteById(anyLong());
    }

    @Test
    public void saveOrUpdateIngredient() throws Exception{
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1l);
        ingredientCommand.setRecipeId(1l);
        ingredientCommand.setDescription("");

        when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);

        mockMvc.perform(post("/recipe/1/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id",Long.toString(1l))
                .param("description","")
        ).andExpect(view().name("redirect:/recipe/1/ingredients/1/show"))
        .andExpect(status().is3xxRedirection());

        verify(ingredientService,times(1)).saveIngredientCommand(any());
    }
}
