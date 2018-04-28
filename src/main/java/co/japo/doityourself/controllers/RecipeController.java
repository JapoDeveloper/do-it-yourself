package co.japo.doityourself.controllers;

import co.japo.doityourself.services.MathService;
import co.japo.doityourself.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class RecipeController {

    private RecipeService recipeService;
    private MathService mathService;

    public RecipeController(RecipeService recipeService,MathService mathService){
        this.recipeService = recipeService;
        this.mathService = mathService;
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
        model.addAttribute("recipe",recipeService.getById(new Long(id)).get());
        model.addAttribute("mathService",mathService);
        return "recipe/show";
    }
}
