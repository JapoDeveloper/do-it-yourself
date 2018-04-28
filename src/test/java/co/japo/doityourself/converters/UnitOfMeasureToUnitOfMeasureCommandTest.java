package co.japo.doityourself.converters;

import co.japo.doityourself.commands.CategoryCommand;
import co.japo.doityourself.commands.UnitOfMeasureCommand;
import co.japo.doityourself.domain.Category;
import co.japo.doityourself.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private final long ID = 1l;
    private final String UOM = "Teaspoon";
    private UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp(){
        this.converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert(){
        UnitOfMeasure source = new UnitOfMeasure();
        source.setId(ID);
        source.setUom(UOM);

        UnitOfMeasureCommand result = converter.convert(source);

        assertEquals(ID,result.getId().longValue());
        assertEquals(UOM, result.getUom());
    }

}
