package com.pizzaproject.domain.exception;


public class PizzaNotFoundException extends RuntimeException {

    public PizzaNotFoundException(String name) {
        super("Could not find pizza " + name);
    }
}
