package com.techelevator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techelevator.dao.RecipeDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeAPI;
import com.techelevator.service.RecipeAPIService;
import com.techelevator.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RecipeAPIController {

    @Autowired
    RecipeDAO recipeDAO;

    @Autowired
    RecipeAPIService recipeAPIService;

    @Autowired
    UserDAO userDAO;

    public RecipeAPIController (RecipeAPIService recipeAPIService, UserDAO userDAO) {
        this.recipeAPIService = recipeAPIService;
        this.userDAO = userDAO;
    }

    @RequestMapping(path="/recipes", method = RequestMethod.GET)
    public List<RecipeAPI> recipeAPIList (@RequestParam String ingredients, @RequestParam int limitNumber) throws JsonProcessingException {
        return recipeAPIService.getRecipesSearchByIngredients(ingredients, limitNumber);
    }



}
