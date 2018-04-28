package co.japo.doityourself.repositories;

import co.japo.doityourself.domain.UnitOfMeasure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    private UnitOfMeasureRepository uomRepository;

    @Test
    public void findByUomIgnoreCase(){
        Optional<UnitOfMeasure> uomOptional = uomRepository.findByUomIgnoreCase("teaspoon");
        assertEquals("Teaspoon",uomOptional.get().getUom());
    }
}
