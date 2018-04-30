package co.japo.doityourself.controllers;

import co.japo.doityourself.commands.RecipeCommand;
import co.japo.doityourself.domain.Recipe;
import co.japo.doityourself.services.DataConverterService;
import co.japo.doityourself.services.MathService;
import co.japo.doityourself.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
public class RecipeController {

    private RecipeService recipeService;
    private MathService mathService;
    private DataConverterService dataConverterService;

    public RecipeController(RecipeService recipeService,MathService mathService, DataConverterService dataConverterService){
        this.recipeService = recipeService;
        this.mathService = mathService;
        this.dataConverterService = dataConverterService;
    }

    @GetMapping({"","/"})
    public String listRecipes(Model model){
        log.info("List recipes endpoint of RecipeController class was invoked.");
        model.addAttribute("allRecipes", recipeService.list());
        return "recipe/list";
    }

    @GetMapping("/recipe/new")
    public String createNewRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/save";
    }

    @GetMapping("/recipe/{id}/show")
    public String showRecipeById(@PathVariable String id, Model model){
        log.debug("Get recipe by id endpoint of RecipeController class was invoked.");
            Recipe recipe = recipeService.getById(new Long(id));
            model.addAttribute("recipe", recipe);
            model.addAttribute("mathService", mathService);
            if (recipe.getImage() != null) {
                model.addAttribute("recipeImage", dataConverterService.getBase64(recipe.getImage()));
            }
            return "recipe/show";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        log.debug("Update recipe by id endpoint of RecipeController was invoked.");
            model.addAttribute("recipe", recipeService.getCommandById(new Long(id)));
            return "recipe/save";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable String id){
        log.debug("Delete recipe by id endpoint of RecipeController was invoked.");
        recipeService.deleteById(new Long(id));
        return "redirect:/";
    }

    @PostMapping("/recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand){
        log.debug("Save or update recipe endpoint of RecipeController was invoked.");
        RecipeCommand saved = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/"+saved.getId()+"/show";
    }
}
