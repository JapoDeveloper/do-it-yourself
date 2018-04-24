package co.japo.doityourself.controllers;

import co.japo.doityourself.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
public class RecipeController {

    private static final Logger LOG = Logger.getLogger(RecipeController.class.getName());
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipes")
    public String listRecipes(Model model){
        LOG.info("Method listRecipes(Model model) of RecipeController class was invoked.");
        model.addAttribute("allRecipes", recipeService.list());
        return "recipes";
    }
}
