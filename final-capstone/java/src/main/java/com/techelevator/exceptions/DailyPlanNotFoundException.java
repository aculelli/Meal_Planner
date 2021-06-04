package com.techelevator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Daily plan not found.")
public class DailyPlanNotFoundException extends Exception {
    public DailyPlanNotFoundException() {
        super("Daily plan not found.");
    }
}
