package co.japo.doityourself.converters;

import co.japo.doityourself.commands.IngredientCommand;
import co.japo.doityourself.domain.Ingredient;
import co.japo.doityourself.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand,Ingredient> {

    private UnitOfMeasureCommandToUnitOfMeasure converter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure converter){
        this.converter = converter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if(source == null){
            return null;
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setDescription(source.getDescription());
        ingredient.setAmount(source.getAmount());
        ingredient.setUom(converter.convert(source.getUom()));
        if(source.getRecipeId() != null){
            ingredient.setRecipe(new Recipe(source.getRecipeId()));
        }
        return ingredient;
    }
}
