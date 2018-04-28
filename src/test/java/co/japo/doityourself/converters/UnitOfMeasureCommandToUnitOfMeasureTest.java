package co.japo.doityourself.converters;

import co.japo.doityourself.commands.UnitOfMeasureCommand;
import co.japo.doityourself.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private final long ID = 1l;
    private final String UOM = "Teaspoon";
    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp(){
        this.converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert(){
        UnitOfMeasureCommand source = new UnitOfMeasureCommand();
        source.setId(ID);
        source.setUom(UOM);

        UnitOfMeasure result = converter.convert(source);

        assertEquals(ID,result.getId().longValue());
        assertEquals(UOM, result.getUom());
    }

}
