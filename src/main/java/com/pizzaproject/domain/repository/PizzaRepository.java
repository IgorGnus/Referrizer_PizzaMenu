package com.pizzaproject.domain.repository;

import com.pizzaproject.domain.model.DAOPizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<DAOPizza,String> {
    DAOPizza findByNameOrderByDateDesc(String name);
    DAOPizza findBySlug(String slug);
    void deleteByName(String name);
    @Override
    boolean existsById(String s);

}
