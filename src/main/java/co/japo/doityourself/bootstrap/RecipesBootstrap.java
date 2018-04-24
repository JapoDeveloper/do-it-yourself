package co.japo.doityourself.bootstrap;

import co.japo.doityourself.domain.*;
import co.japo.doityourself.repositories.CategoryRepository;
import co.japo.doityourself.repositories.RecipeRepository;
import co.japo.doityourself.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class RecipesBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository uomRepository;
    private RecipeRepository recipeRepository;

    public RecipesBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository uomRepository,
                              RecipeRepository recipeRepository){
        this.categoryRepository = categoryRepository;
        this.uomRepository = uomRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.save(getGuacamoleRecipe());
        recipeRepository.save(getSpicyGrilledChickenTacosRecipe());
    }

    public Recipe getGuacamoleRecipe(){
        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setCategories(new HashSet<Category>(){
            {
                add(categoryRepository.findByCategoryNameIgnoreCase("dip").get());
                add(categoryRepository.findByCategoryNameIgnoreCase("mexican").get());
                add(categoryRepository.findByCategoryNameIgnoreCase("vegan").get());
                add(categoryRepository.findByCategoryNameIgnoreCase("avocado").get());
            }
        });
        perfectGuacamole.setCookTime(0);
        perfectGuacamole.setDescription("Perfect Guacamole Recipe");
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");
        perfectGuacamole.addIngredient(new Ingredient(new BigDecimal(2),"ripe avocados"));
        perfectGuacamole.addIngredient(new Ingredient(new BigDecimal(0.5),"Kosher salt", uomRepository.findByUomIgnoreCase("teaspoon").get()));
        perfectGuacamole.addIngredient(new Ingredient(new BigDecimal(1),"fresh lime juice or lemon juice", uomRepository.findByUomIgnoreCase("tablespoon").get()));
        perfectGuacamole.addIngredient(new Ingredient(new BigDecimal(2),"minced red onion or thinly sliced green onion", uomRepository.findByUomIgnoreCase("tablespoon").get()));
        perfectGuacamole.addIngredient(new Ingredient(new BigDecimal(1),"serrano chiles, stems and seeds removed, minced"));
        perfectGuacamole.addIngredient(new Ingredient(new BigDecimal(2),"cilantro (leaves and tender stems), finely chopped", uomRepository.findByUomIgnoreCase("tablespoon").get()));
        perfectGuacamole.addIngredient(new Ingredient(new BigDecimal(1),"freshly grated black pepper", uomRepository.findByUomIgnoreCase("dash").get()));
        perfectGuacamole.addIngredient(new Ingredient(new BigDecimal(0.5),"ripe tomato, seeds and pulp removed, chopped"));

        perfectGuacamole.setNotes(new Notes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great."));
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setServings(2);
        perfectGuacamole.setSource("Simply Recipes");
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        return perfectGuacamole;
    }

    public Recipe getSpicyGrilledChickenTacosRecipe(){
        Recipe sgct = new Recipe();
        sgct.setCategories(new HashSet<Category>(){
            {
                add(categoryRepository.findByCategoryNameIgnoreCase("dinner").get());
                add(categoryRepository.findByCategoryNameIgnoreCase("grill").get());
                add(categoryRepository.findByCategoryNameIgnoreCase("quick and easy").get());
                add(categoryRepository.findByCategoryNameIgnoreCase("chicken").get());
            }
        });
        sgct.setCookTime(15);
        sgct.setDescription("Spicy Grilled Chicken Tacos");
        sgct.setDifficulty(Difficulty.EASY);
        sgct.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n");
        sgct.addIngredient(new Ingredient(new BigDecimal(2),"ancho chili powder",uomRepository.findByUomIgnoreCase("tablespoon").get()));
        sgct.addIngredient(new Ingredient(new BigDecimal(1),"dried oregano",uomRepository.findByUomIgnoreCase("teaspoon").get()));
        sgct.addIngredient(new Ingredient(new BigDecimal(1),"ried cumin",uomRepository.findByUomIgnoreCase("teaspoon").get()));
        sgct.addIngredient(new Ingredient(new BigDecimal(1),"sugar",uomRepository.findByUomIgnoreCase("teaspoon").get()));
        sgct.addIngredient(new Ingredient(new BigDecimal(0.5),"salt",uomRepository.findByUomIgnoreCase("teaspoon").get()));
        sgct.addIngredient(new Ingredient(new BigDecimal(1),"clove garlic, finely chopped"));
        sgct.addIngredient(new Ingredient(new BigDecimal(1),"finely grated orange zest",uomRepository.findByUomIgnoreCase("tablespoon").get()));
        sgct.addIngredient(new Ingredient(new BigDecimal(3),"fresh-squeezed orange juice",uomRepository.findByUomIgnoreCase("tablespoon").get()));
        sgct.addIngredient(new Ingredient(new BigDecimal(2),"olive oil",uomRepository.findByUomIgnoreCase("tablespoon").get()));
        sgct.addIngredient(new Ingredient(new BigDecimal(1.25),"skinless, boneless chicken thighs",uomRepository.findByUomIgnoreCase("pounds").get()));

        sgct.setNotes(new Notes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "Spicy Grilled Chicken TacosThe ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them."));
        sgct.setPrepTime(20);
        sgct.setServings(4);
        sgct.setSource("Simply Recipes");
        sgct.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        return sgct;
    }


}
