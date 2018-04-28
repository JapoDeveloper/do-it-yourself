package co.japo.doityourself.services;

import co.japo.doityourself.domain.Recipe;
import co.japo.doityourself.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }


    @Override
    public List<Recipe> list(){
        log.debug("Method list() of RecipeServiceImpl class was invoked.");
        List<Recipe> recipes = new ArrayList<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    @Override
    public Optional<Recipe> getById(Long id){
        log.debug("Method getById(Long id) of RecipeServiceImpl class was invoked.");
        return recipeRepository.findById(id);
    }
}
