package co.japo.doityourself.converters;

import co.japo.doityourself.commands.UnitOfMeasureCommand;
import co.japo.doityourself.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure,UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if(source == null){
            return null;
        }
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(source.getId());
        unitOfMeasureCommand.setUom(source.getUom());
        return unitOfMeasureCommand;
    }
}