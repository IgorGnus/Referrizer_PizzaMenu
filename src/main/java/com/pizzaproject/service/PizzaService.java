package com.pizzaproject.service;

import com.pizzaproject.domain.dto.PizzaDto;
import com.pizzaproject.domain.model.DAOPizza;

import java.util.List;

public interface PizzaService {
    void deletePizzaByName(String name);
    DAOPizza getPizzaByName(String name);
    DAOPizza savePizza(PizzaDto pizzaDto);
    List<DAOPizza> getAllPizzas();
    void patchPizzaBySlug(String slug, PizzaDto pizzaDto);
    boolean existsBySlug(String slug);
}
