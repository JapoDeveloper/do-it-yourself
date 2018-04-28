package co.japo.doityourself.converters;

import co.japo.doityourself.commands.CategoryCommand;
import co.japo.doityourself.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryCommandToCategoryTest {

    private final long ID = 1l;
    private final String CATEGORY_NAME = "Mexican";
    private CategoryCommandToCategory converter;

    @Before
    public void setUp(){
        this.converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert(){
        CategoryCommand source = new CategoryCommand();
        source.setId(ID);
        source.setCategoryName(CATEGORY_NAME);

        Category result = converter.convert(source);

        assertEquals(ID,result.getId().longValue());
        assertEquals(CATEGORY_NAME, result.getCategoryName());
    }

}
