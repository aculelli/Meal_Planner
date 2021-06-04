package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.RecipeAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeAPIService {
    @Value("${recipe.api.url}")
    private String apiURL;
    @Value("${recipe.api.key}")
    private String key;

    public List<RecipeAPI> getRecipesSearchByIngredients(String searchString, int limitNumber) throws JsonProcessingException {
        String url = apiURL + "complexSearch?" + key + "&instructionsRequired=true&includeIngredients=" +
                searchString + "&number=" + limitNumber;
        System.out.println(url);

        HttpEntity<String> httpEntity = new HttpEntity<>("");
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        JsonNode jsonNode;
        List<RecipeAPI> recipeAPIList = new ArrayList<>();
        System.out.println(response);

        jsonNode = objectMapper.readTree(response.getBody());

        JsonNode root = jsonNode.path("results");

        for (int i = 0; i < root.size(); i++){
            String id = root.path(i).path("id").asText();
            String title = root.path(i).path("title").asText();

            long longId = Long.parseLong(id);

        RecipeAPI recipeAPI = new RecipeAPI(longId, title);
        recipeAPIList.add(recipeAPI);

        }System.out.println(recipeAPIList);
        return recipeAPIList;
    }


}
