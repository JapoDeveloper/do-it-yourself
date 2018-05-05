package co.japo.doityourself.services;

import co.japo.doityourself.commands.UnitOfMeasureCommand;
import co.japo.doityourself.converters.UnitOfMeasureToUnitOfMeasureCommand;
import co.japo.doityourself.domain.UnitOfMeasure;
import co.japo.doityourself.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceTest {

    private UnitOfMeasureService uomService;
    @Mock
    private UnitOfMeasureRepository uomRepository;
    private UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
        uomService = new UnitOfMeasureServiceImpl(uomRepository,converter);

    }

    @Test
    public void listAllUom() {
        List<UnitOfMeasure> uoms = new ArrayList<>();

        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1l);
        uoms.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2l);
        uoms.add(uom2);

        when(uomRepository.findAll()).thenReturn(uoms);

        List<UnitOfMeasureCommand> result = uomService.listAllUom();
        assertEquals(uoms.size(),result.size());
        verify(uomRepository,times(1)).findAll();
    }
}