package co.japo.doityourself.controllers;

import co.japo.doityourself.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Slf4j
@Controller
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipes")
    public String listRecipes(Model model){
        log.info("List recipes endpoint of RecipeController class was invoked.");
        model.addAttribute("allRecipes", recipeService.list());
        return "list-recipes";
    }
}
