package co.japo.doityourself.services;

import co.japo.doityourself.commands.IngredientCommand;
import co.japo.doityourself.converters.IngredientCommandToIngredient;
import co.japo.doityourself.converters.IngredientToIngredientCommand;
import co.japo.doityourself.domain.Ingredient;
import co.japo.doityourself.repositories.IngredientRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceTest {

    private IngredientService ingredientService;

    @Mock
    private IngredientRepository ingredientRepository;
    @Mock
    private IngredientToIngredientCommand ingredientCommandConverter;
    @Mock
    private IngredientCommandToIngredient ingredientConverter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientRepository,ingredientCommandConverter,ingredientConverter);
    }

    @Test
    public void findByRecipeId() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1l);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1l);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);

        when(ingredientRepository.findByRecipeId(anyLong())).thenReturn(ingredients);
        when(ingredientCommandConverter.convert(any())).thenReturn(ingredientCommand);

        assertThat("List of ingredients empty when try to return with the recipe id",
                ingredients.size(),equalTo(ingredientService.findByRecipeId(1l).size()));
        verify(ingredientRepository,times(1)).findByRecipeId(anyLong());

    }

    @Test
    public void findByRecipeIdAndIngredientId() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(3l);
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(3l);

        when(ingredientRepository.findByRecipeIdAndId(anyLong(),anyLong())).thenReturn(Optional.of(ingredient));
        when(ingredientCommandConverter.convert(any())).thenReturn(ingredientCommand);

        assertEquals(ingredientCommand.getId(),ingredientService.findByRecipeIdAndIngredientId(1l,3l).getId());
        verify(ingredientRepository,times(1)).findByRecipeIdAndId(anyLong(),anyLong());
    }

    @Test
    public void deleteById(){
        Long ingredientId = 1l;
        ingredientService.deleteById(ingredientId);
        verify(ingredientRepository,times(1)).deleteById(anyLong());
    }
}