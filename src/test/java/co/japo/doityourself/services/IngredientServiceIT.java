package co.japo.doityourself.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientServiceIT {

    @Autowired
    private IngredientService ingredientService;

    @Before
    public void setUp(){

    }

    @Test
    public void saveIngredientCommand() throws Exception{
        //Todo implement the test
    }
}
