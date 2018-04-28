package co.japo.doityourself.converters;

import co.japo.doityourself.commands.CategoryCommand;
import co.japo.doityourself.commands.IngredientCommand;
import co.japo.doityourself.commands.RecipeCommand;
import co.japo.doityourself.domain.Category;
import co.japo.doityourself.domain.Ingredient;
import co.japo.doityourself.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand,Recipe> {

    private CategoryCommandToCategory categoryConverter;
    private IngredientCommandToIngredient ingredientConverter;
    private NotesCommandToNotes noteConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes noteConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.noteConverter = noteConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if(source == null){
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setImage(source.getImage());
        if(source.getNotes() != null) {
            recipe.setNotes(noteConverter.convert(source.getNotes()));
        }

        if(source.getIngredients() != null){
            source.getIngredients().forEach(
                    (IngredientCommand i) -> recipe.getIngredients().add(ingredientConverter.convert(i))
            );
        }

        if(source.getCategories() != null) {
            source.getCategories().forEach(
                    (CategoryCommand c) -> recipe.getCategories().add(categoryConverter.convert(c))
            );
        }

        return recipe;
    }

}
