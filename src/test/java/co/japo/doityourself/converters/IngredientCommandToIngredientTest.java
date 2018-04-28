package co.japo.doityourself.converters;

import co.japo.doityourself.commands.IngredientCommand;
import co.japo.doityourself.commands.UnitOfMeasureCommand;
import co.japo.doityourself.domain.Ingredient;
import co.japo.doityourself.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class IngredientCommandToIngredientTest {

    private final long ID = 1l;
    private final String DESCRIPTION = "Some text";
    private final BigDecimal AMOUNT = new BigDecimal(1);
    private final long UOM_ID = 1l;
    private IngredientCommandToIngredient converter;

    @Before
    public void setUp(){
        this.converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convertWithoutUOM(){
        IngredientCommand source = new IngredientCommand();
        source.setId(ID);
        source.setDescription(DESCRIPTION);
        source.setAmount(AMOUNT);
        source.setUom(null);

        Ingredient result = converter.convert(source);

        assertEquals(ID,result.getId().longValue());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(AMOUNT,result.getAmount());
        assertNull(result.getUom());
    }

    @Test
    public void convertWithUOM(){
        IngredientCommand source = new IngredientCommand();
        source.setId(ID);
        source.setDescription(DESCRIPTION);
        source.setAmount(AMOUNT);

        UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
        uom.setId(UOM_ID);

        source.setUom(uom);

        Ingredient result = converter.convert(source);

        assertEquals(ID,result.getId().longValue());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(AMOUNT,result.getAmount());
        assertNotNull(result.getUom());
        assertEquals(UOM_ID,result.getUom().getId().longValue());
    }
}
