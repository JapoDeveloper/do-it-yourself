package co.japo.doityourself.controllers;

import co.japo.doityourself.services.IngredientService;
import co.japo.doityourself.services.MathService;
import co.japo.doityourself.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;
    private MathService mathService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, MathService mathService){
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.mathService = mathService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String displayListOfIngredients(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.getCommandById(new Long(id)));
        model.addAttribute("mathService",mathService);
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredients/{ingredientId}/show")
    public String displayIngredientDetails(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(
                Long.valueOf(recipeId), Long.valueOf(ingredientId)
        ));
        model.addAttribute("mathService",mathService);
        return "recipe/ingredient/show";
    }
}
