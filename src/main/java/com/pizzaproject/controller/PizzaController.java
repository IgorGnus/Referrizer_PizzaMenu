package com.pizzaproject.controller;

import com.pizzaproject.PizzaProjectApplication;
import com.pizzaproject.domain.dto.PizzaDto;
import com.pizzaproject.domain.exception.SlugInUseException;
import com.pizzaproject.domain.model.DAOPizza;
import com.pizzaproject.service.PizzaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class PizzaController {
    private static final Logger logger = LoggerFactory.getLogger(PizzaProjectApplication.class);

    @Autowired
    private PizzaServiceImpl pizzaService;

    @GetMapping("/PizzaMenu")
    List<DAOPizza> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @PostMapping("/PizzaMenu")
    @ResponseStatus(HttpStatus.CREATED)
    DAOPizza newPizza(@RequestBody @Valid PizzaDto pizzaDto) {
        if(pizzaService.existsBySlug(pizzaDto.getSlug())){
            throw new SlugInUseException(pizzaDto.getSlug());
        }
        logger.info("Pizza added successfully");
        return pizzaService.savePizza(pizzaDto);
    }

    @GetMapping("/PizzaMenu/{name}")
    DAOPizza onePizza(@PathVariable String name) {
       return pizzaService.getPizzaByName(name);
    }

    @DeleteMapping("/PizzaMenu/{name}")
    void deletePizza(@PathVariable String name){

        pizzaService.deletePizzaByName(name);
        logger.info("Pizza deleted successfully");
    }

    @PatchMapping("/PizzaMenu/{slug}")
    public void patchResource(@PathVariable String slug, @RequestBody @Valid PizzaDto pizzaDto) {
        if(!(slug.equals(pizzaDto.getSlug()))){
            logger.error("Slug doesn't match url");
        }
        else {
            pizzaService.patchPizzaBySlug(slug, pizzaDto);
            logger.info("Pizza menu item changed successfully");
        }

    }

}
