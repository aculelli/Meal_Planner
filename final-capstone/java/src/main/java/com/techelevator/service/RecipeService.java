package com.techelevator.service;

import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeAPI;
import com.techelevator.model.RecipeIngredient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

//@Component
public class RecipeService {

    private final String BASE_URL = "https://api.spoonacular.com/recipes/";
    private final String KEY = "apiKey=49bdab8bd430430189428b3e4f1492d9";
    private RestTemplate restTemplate = new RestTemplate(); //server communication

    // do we declare the BASE_URL in the Application (like the client side of TEnmo  -- see AccountsService in TEnmo)
    // so we can create an empty one here, and call + concatenate the end points? We cannot create a
    // constructor for RecipeService that has BASE_URL as declared here because it has already been assigned a value (same for KEY)

    // create another Recipe constructor that does not take in a userId so an anon user can getRecipes()
    //ingredients=apples,+flour,+sugar&number=2
    //https://api.spoonacular.com/recipes/complexSearch?apiKey=49bdab8bd430430189428b3e4f1492d9&instructionsRequired=true&includeIngredients=tomato,mozzarella cheese
    //String joinedString = String.join(", ", strArray);

    //WE DON'T NEED THIS ANYMORE. WE DO THIS IN RecipeAPIService
    public RecipeAPI[] listRecipesFromSearch(String ingredientList){
        RecipeAPI[] recipes = null;
        int limitNumber = 0;
        recipes = restTemplate.getForObject(BASE_URL + "complexSearch?" + KEY +
                "&instructionsRequired=true&includeIngredients=" +
                ingredientList + "&number=" + limitNumber, RecipeAPI[].class);

        return recipes;
    }

    //rest getForObject etc


    //HELPER METHODS

    private HttpEntity makeAuthEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
    // we may need to change the 2nd param name (String token)
    private HttpEntity<Recipe> makeRecipeEntity(Recipe recipe, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<Recipe> entity = new HttpEntity<>(recipe, headers);
        return entity;
    }
}
