package co.japo.doityourself.controllers;

import co.japo.doityourself.domain.Recipe;
import co.japo.doityourself.services.DataConverterService;
import co.japo.doityourself.services.MathService;
import co.japo.doityourself.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping({"","/"})
    public String listRecipes(Model model){
        log.info("List recipes endpoint of RecipeController class was invoked.");
        model.addAttribute("allRecipes", recipeService.list());
        return "recipe/list";
    }

    @RequestMapping("/recipe/show/{id}")
    public String getRecipeById(@PathVariable String id, Model model){
        log.info("Get recipe by id endpoint of RecipeController class was invoked.");
        Optional<Recipe> recipe = recipeService.getById(new Long(id));
        if(recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
            model.addAttribute("mathService", mathService);
            if (recipe.get().getImage() != null) {
                model.addAttribute("recipeImage", dataConverterService.getBase64(recipe.get().getImage()));
            }
            return "recipe/show";
        }else{
            throw new RuntimeException("Recipe with id "+id+" doesn't found!");
        }
    }
}
