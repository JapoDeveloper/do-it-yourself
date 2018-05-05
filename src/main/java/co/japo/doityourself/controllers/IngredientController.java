package co.japo.doityourself.controllers;

import co.japo.doityourself.commands.IngredientCommand;
import co.japo.doityourself.services.IngredientService;
import co.japo.doityourself.services.MathService;
import co.japo.doityourself.services.RecipeService;
import co.japo.doityourself.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;
    private UnitOfMeasureService uomService;
    private MathService mathService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService,UnitOfMeasureService uomService, MathService mathService){
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
        this.mathService = mathService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String displayListOfIngredients(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.getCommandById(new Long(id)));
        model.addAttribute("mathService",mathService);
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String createNewIngredient(Model model){
        model.addAttribute("ingredient", new IngredientCommand());
        model.addAttribute("listOfUoms", uomService.listAllUom());
        return "recipe/ingredient/save";
    }

    @GetMapping("/recipe/{recipeId}/ingredients/{ingredientId}/show")
    public String displayIngredientDetails(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(
                Long.valueOf(recipeId), Long.valueOf(ingredientId)
        ));
        model.addAttribute("mathService",mathService);
        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredients/{ingredientId}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(
                new Long(recipeId), new Long(ingredientId)
        ));
        model.addAttribute("listOfUoms", uomService.listAllUom());
        return "recipe/ingredient/save";
    }

    @GetMapping("/recipe/{recipeId}/ingredients/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId){
        ingredientService.deleteById(new Long(ingredientId));
        return "redirect:/recipe/"+recipeId+"/ingredients";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdateIngredient(@PathVariable String recipeId, @ModelAttribute IngredientCommand ingredientCommand){
        ingredientCommand.setRecipeId(new Long(recipeId));
        IngredientCommand saved = ingredientService.saveIngredientCommand(ingredientCommand);
        return "redirect:/recipe/"+ingredientCommand.getRecipeId()+"/ingredients/"+saved.getId()+"/show";
    }
}
