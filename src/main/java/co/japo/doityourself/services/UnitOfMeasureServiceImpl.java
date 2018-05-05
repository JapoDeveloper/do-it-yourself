package co.japo.doityourself.services;

import co.japo.doityourself.commands.UnitOfMeasureCommand;
import co.japo.doityourself.converters.UnitOfMeasureToUnitOfMeasureCommand;
import co.japo.doityourself.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{

    private UnitOfMeasureRepository uomRepository;
    private UnitOfMeasureToUnitOfMeasureCommand uomCommandConverter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uomRepository, UnitOfMeasureToUnitOfMeasureCommand uomCommandConverter) {
        this.uomRepository = uomRepository;
        this.uomCommandConverter = uomCommandConverter;
    }

    @Override
    public List<UnitOfMeasureCommand> listAllUom() {
        return StreamSupport.stream(uomRepository.findAll().spliterator(),false)
                .map(uomCommandConverter::convert)
                .collect(Collectors.toList());
    }

}
