package co.japo.doityourself.converters;

import co.japo.doityourself.commands.*;
import co.japo.doityourself.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeToRecipeCommandTest {

    private final long ID = 1l;
    private final String DESCRIPTION = "Some text";
    private final int PREP_TIME = 5;
    private final int COOK_TIME = 10;
    private final int SERVINGS = 3;
    private final String SOURCE = "Source";
    private final String URL = "http://url.com";
    private final String DIRECTIONS = "Some text";
    private final Difficulty DIFFICULTY = Difficulty.EASY;
    private final byte[] IMAGE = new byte[10];
    private final long NOTE_ID = 1l;
    private final long INGREDIENT_1_ID = 1l;
    private final long INGREDIENT_2_ID = 2l;
    private final long CATEGORY_1_ID = 1l;
    private final long CATEGORY_2_ID = 2l;
    private RecipeToRecipeCommand converter;

    @Before
    public void setUp(){
        this.converter = new RecipeToRecipeCommand(
                new CategoryToCategoryCommand(), new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand()
        );
    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert(){
        Recipe source = new Recipe();
        source.setId(ID);
        source.setDescription(DESCRIPTION);
        source.setPrepTime(PREP_TIME);
        source.setCookTime(COOK_TIME);
        source.setServings(SERVINGS);
        source.setSource(SOURCE);
        source.setUrl(URL);
        source.setDirections(DIRECTIONS);
        source.setDifficulty(DIFFICULTY);
        source.setImage(IMAGE);

        Notes note = new Notes();
        note.setId(NOTE_ID);

        source.setNotes(note);

        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        ingredient1.setId(INGREDIENT_1_ID);
        ingredient2.setId(INGREDIENT_2_ID);

        source.getIngredients().add(ingredient1);
        source.getIngredients().add(ingredient2);

        Category category1 = new Category();
        Category category2 = new Category();
        category1.setId(CATEGORY_1_ID);
        category2.setId(CATEGORY_2_ID);

        source.getCategories().add(category1);
        source.getCategories().add(category2);

        RecipeCommand result = converter.convert(source);

        assertEquals(ID,result.getId().longValue());
        assertEquals(DESCRIPTION,result.getDescription());
        assertEquals(PREP_TIME,result.getPrepTime().intValue());
        assertEquals(COOK_TIME,result.getCookTime().intValue());
        assertEquals(SERVINGS, result.getServings().intValue());
        assertEquals(SOURCE,result.getSource());
        assertEquals(URL,source.getUrl());
        assertEquals(DIRECTIONS,source.getDirections());
        assertEquals(DIFFICULTY,result.getDifficulty());
        assertEquals(IMAGE.length,result.getImage().length);
        assertNotNull(result.getNotes());
        assertEquals(NOTE_ID,result.getNotes().getId().longValue());
        assertEquals(2,result.getIngredients().size());
        assertEquals(2,result.getCategories().size());
    }
}


