package com.techelevator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Recipe not found.")
public class RecipeNotFoundException extends Exception {

    public RecipeNotFoundException() {
        super("Recipe not found.");
    }
}
