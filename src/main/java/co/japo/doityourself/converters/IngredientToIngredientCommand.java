package co.japo.doityourself.converters;

import co.japo.doityourself.commands.IngredientCommand;
import co.japo.doityourself.commands.UnitOfMeasureCommand;
import co.japo.doityourself.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient,IngredientCommand> {

    private UnitOfMeasureToUnitOfMeasureCommand converter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand converter){
        this.converter = converter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if(source == null){
            return null;
        }
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(source.getId());
        if(source.getRecipe() != null){
            ingredientCommand.setRecipeId(source.getRecipe().getId());
        }
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setUom(converter.convert(source.getUom()));
        return ingredientCommand;
    }
}
