package com.restaurant.service;

import com.restaurant.model.PizzaDto;
import com.restaurant.repositoriy.IPizzaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PizzaService implements IPizzaService {

    private final IPizzaDao pizzaDao;

    @Autowired
    public PizzaService(IPizzaDao pizzaDao) {
        this.pizzaDao = pizzaDao;
    }

    @Override
    public Long upsertPizza(PizzaDto pizzaDto) {
        PizzaDto persistedPizza = Objects.nonNull(pizzaDto.getId())
                ? pizzaDao.update(pizzaDto)
                : pizzaDao.insert(pizzaDto);
        return Optional.ofNullable(persistedPizza)
                .map(PizzaDto::getId)
                .orElse(0L);
    }

    @Override
    public PizzaDto getPizza(Long pizzaId) {
        return pizzaDao.get(pizzaId);
    }

    @Override
    public List<PizzaDto> list() {
        return pizzaDao.getAllPizzas();
    }

    @Override
    public Boolean delete(Long pizzaId) {
        return Objects.nonNull(pizzaDao.delete(pizzaId));
    }

}
