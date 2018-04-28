package co.japo.doityourself.converters;

import co.japo.doityourself.commands.CategoryCommand;
import co.japo.doityourself.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryToCategoryCommandTest {

    private final long ID = 1l;
    private final String CATEGORY_NAME = "Mexican";
    private CategoryToCategoryCommand converter;

    @Before
    public void setUp(){
        this.converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert(){
        Category source = new Category();
        source.setId(ID);
        source.setCategoryName(CATEGORY_NAME);

        CategoryCommand result = converter.convert(source);

        assertEquals(ID,result.getId().longValue());
        assertEquals(CATEGORY_NAME, result.getCategoryName());
    }

}
