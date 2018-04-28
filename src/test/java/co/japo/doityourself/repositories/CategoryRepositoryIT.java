package co.japo.doityourself.repositories;

import co.japo.doityourself.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryIT {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByCategoryNameIgnoreCase(){
        Optional<Category> categoryOptional = categoryRepository.findByCategoryNameIgnoreCase("american");
        assertEquals("American",categoryOptional.get().getCategoryName());
    }
}
