package com.exampleApp.purchaseService.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StockNotEnoughException extends RuntimeException {

    public StockNotEnoughException(String message) {
        super(message);
    }
}
