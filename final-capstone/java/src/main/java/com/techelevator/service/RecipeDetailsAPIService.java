package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeDetailsAPI;
import com.techelevator.model.RecipeIngredient;
import com.techelevator.model.RecipeIngredientAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeDetailsAPIService {
    @Value("${recipe.api.url}")
    private String apiURL;
    @Value("${recipe.api.key}")
    private String key;

    public RecipeDetailsAPI getRecipeDetailsById(long id) throws JsonProcessingException {
        String url = apiURL + id + "/information?" + key;
        System.out.println(url);

        HttpEntity<String> httpEntity = new HttpEntity<>("");
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        JsonNode jsonNode;
        List<RecipeIngredientAPI> extendedIngredientsList = new ArrayList<>();
        System.out.println(response);

        jsonNode = objectMapper.readTree(response.getBody());

        JsonNode extendedIngredientsRoot = jsonNode.path("extendedIngredients");

        String recipeId = jsonNode.path("id").asText();
        long longId = Long.parseLong(recipeId);

        String recipeName = jsonNode.path("title").asText();

        String recipeServingSize = jsonNode.path("servings").asText();
        int recipeServingSizeInt = Integer.parseInt(recipeServingSize);

        String directions = jsonNode.path("instructions").asText();

        for (int i = 0; i < extendedIngredientsRoot.size(); i++) {
            String ingredientName = extendedIngredientsRoot.path(i).path("originalName").asText();
            String amount = extendedIngredientsRoot.path(i).path("amount").asText();
            String unit = extendedIngredientsRoot.path(i).path("unit").asText();

            double amountAsDouble = Double.parseDouble(amount);
            RecipeIngredientAPI recipeIngredient = new RecipeIngredientAPI(ingredientName, amountAsDouble, unit);

            extendedIngredientsList.add(recipeIngredient);
        }

        RecipeDetailsAPI recipe = new RecipeDetailsAPI(longId, recipeName, directions, extendedIngredientsList, recipeServingSizeInt);

        return recipe;
    }
}
