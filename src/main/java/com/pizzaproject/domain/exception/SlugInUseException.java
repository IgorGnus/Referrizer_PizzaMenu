package com.pizzaproject.domain.exception;

public class SlugInUseException extends RuntimeException {
    public SlugInUseException(String name) {
        super(name + " : Slug already in use " );
    }
}
