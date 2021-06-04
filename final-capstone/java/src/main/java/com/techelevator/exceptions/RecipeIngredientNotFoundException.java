package com.techelevator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Recipe ingredient not found.")
public class RecipeIngredientNotFoundException extends Exception{


        public RecipeIngredientNotFoundException() {
            super("Recipe ingredient not found.");
        }



}
