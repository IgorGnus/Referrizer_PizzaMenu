package com.pizzaproject.service;

import com.pizzaproject.domain.dto.PizzaDto;
import com.pizzaproject.domain.repository.PizzaRepository;
import com.pizzaproject.domain.model.DAOPizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class PizzaServiceImpl implements PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    @CacheEvict(value = "pizza",allEntries = true)
    public void deletePizzaByName(String name) {
        pizzaRepository.deleteByName(name);
    }

    @Override
    @Cacheable("pizza")
    public DAOPizza getPizzaByName(String name) {
        return pizzaRepository.findByNameOrderByDateDesc(name);
    }

    @Override
    @CacheEvict(value = "pizza",allEntries = true)
    public DAOPizza savePizza(PizzaDto dto) {
        var pizza = DAOPizza.builder()
                .name(dto.getName())
                .size(dto.getSize())
                .price(dto.getPrice())
                .slug(dto.getSlug())
                .date(Instant.now().truncatedTo(ChronoUnit.SECONDS))
                .build();
        return pizzaRepository.save(pizza);
    }

    @Override
    public List<DAOPizza> getAllPizzas() {
        return pizzaRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    @CacheEvict(value = "pizza",allEntries = true)
    public void patchPizzaBySlug(String slug, PizzaDto pizzaDto){
        DAOPizza newPizza = pizzaRepository.findBySlug(slug);
        boolean needUpdate = false;

        if (StringUtils.hasLength(pizzaDto.getName())) {
            newPizza.setName(pizzaDto.getName());
            needUpdate = true;
        }
        if (pizzaDto.getPrice()!=null) {
            newPizza.setPrice(pizzaDto.getPrice());
            needUpdate = true;
        }
        if (pizzaDto.getSize()!=null) {
            newPizza.setPrice(pizzaDto.getPrice());
            needUpdate = true;
        }
        if (needUpdate) {
            //Auto update date
            pizzaDto.setDate(Instant.now().truncatedTo(ChronoUnit.SECONDS));
            pizzaRepository.save(newPizza);
        }
    }

    public boolean existsBySlug(String slug){
        return pizzaRepository.existsById(slug);
    }
}
