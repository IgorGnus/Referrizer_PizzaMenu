package com.pizzaproject.domain.exception;

import java.util.Set;

public class PizzaUnSupportedFieldPatchException extends RuntimeException{

    public PizzaUnSupportedFieldPatchException(Set<String> keys)
    {
        super("Field " + keys.toString() + " update is not allow.");
    }
}
