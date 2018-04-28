package co.japo.doityourself.converters;

import co.japo.doityourself.commands.CategoryCommand;
import co.japo.doityourself.commands.IngredientCommand;
import co.japo.doityourself.domain.Category;
import co.japo.doityourself.domain.Ingredient;
import co.japo.doityourself.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class IngredientToIngredientCommandTest {

    private final long ID = 1l;
    private final String DESCRIPTION = "Some text";
    private final BigDecimal AMOUNT = new BigDecimal(1);
    private final long UOM_ID = 1l;
    private IngredientToIngredientCommand converter;

    @Before
    public void setUp(){
        this.converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convertWithoutUOM(){
        Ingredient source = new Ingredient();
        source.setId(ID);
        source.setDescription(DESCRIPTION);
        source.setAmount(AMOUNT);
        source.setUom(null);

        IngredientCommand result = converter.convert(source);

        assertEquals(ID,result.getId().longValue());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(AMOUNT,result.getAmount());
        assertNull(result.getUom());
    }

    @Test
    public void convertWithUOM(){
        Ingredient source = new Ingredient();
        source.setId(ID);
        source.setDescription(DESCRIPTION);
        source.setAmount(AMOUNT);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);

        source.setUom(uom);

        IngredientCommand result = converter.convert(source);

        assertEquals(ID,result.getId().longValue());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(AMOUNT,result.getAmount());
        assertNotNull(result.getUom());
        assertEquals(UOM_ID,result.getUom().getId().longValue());
    }
}
