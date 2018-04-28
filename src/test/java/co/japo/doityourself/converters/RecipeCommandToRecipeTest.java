package co.japo.doityourself.converters;

import co.japo.doityourself.commands.CategoryCommand;
import co.japo.doityourself.commands.IngredientCommand;
import co.japo.doityourself.commands.NotesCommand;
import co.japo.doityourself.commands.RecipeCommand;
import co.japo.doityourself.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeCommandToRecipeTest {

    private final long ID = 1l;
    private final String DESCRIPTION = "Some text";
    private final int PREP_TIME = 8;
    private final int COOK_TIME = 4;
    private final int SERVINGS = 3;
    private final String SOURCE = "Source";
    private final String URL = "http://url.com";
    private final String DIRECTIONS = "Some text";
    private final Difficulty DIFFICULTY = Difficulty.HARD;
    private final byte[] IMAGE = new byte[100];
    private final long NOTE_ID = 1l;
    private final long INGREDIENT_1_ID = 1l;
    private final long INGREDIENT_2_ID = 2l;
    private final long CATEGORY_1_ID = 1l;
    private final long CATEGORY_2_ID = 2l;
    private RecipeCommandToRecipe converter;

    @Before
    public void setUp(){
        this.converter = new RecipeCommandToRecipe(
                new CategoryCommandToCategory(), new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes()
        );
    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert(){
        RecipeCommand source = new RecipeCommand();
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

        NotesCommand note = new NotesCommand();
        note.setId(NOTE_ID);

        source.setNotes(note);

        IngredientCommand ingredient1 = new IngredientCommand();
        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient1.setId(INGREDIENT_1_ID);
        ingredient2.setId(INGREDIENT_2_ID);

        source.getIngredients().add(ingredient1);
        source.getIngredients().add(ingredient2);

        CategoryCommand category1 = new CategoryCommand();
        CategoryCommand category2 = new CategoryCommand();
        category1.setId(CATEGORY_1_ID);
        category2.setId(CATEGORY_2_ID);

        source.getCategories().add(category1);
        source.getCategories().add(category2);

        Recipe result = converter.convert(source);

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
