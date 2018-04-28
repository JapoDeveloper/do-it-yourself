package co.japo.doityourself.converters;

import co.japo.doityourself.commands.RecipeCommand;
import co.japo.doityourself.domain.Category;
import co.japo.doityourself.domain.Ingredient;
import co.japo.doityourself.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe,RecipeCommand> {

    private CategoryToCategoryCommand categoryConverter;
    private IngredientToIngredientCommand ingredientConverter;
    private NotesToNotesCommand noteConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand noteConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.noteConverter = noteConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null){
            return null;
        }
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setImage(source.getImage());
        recipeCommand.setNotes(noteConverter.convert(source.getNotes()));

        if(source.getIngredients() != null){
            source.getIngredients().forEach(
                    (Ingredient i) -> recipeCommand.getIngredients().add(ingredientConverter.convert(i))
            );
        }

        if(source.getCategories() != null) {
            source.getCategories().forEach(
                    (Category c) -> recipeCommand.getCategories().add(categoryConverter.convert(c))
            );
        }

        return recipeCommand;
    }
}
