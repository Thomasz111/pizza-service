package com.restaurant.service;

import com.restaurant.model.PizzaDto;
import com.restaurant.repositoriy.IPizzaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PizzaService implements IPizzaService {

    private final IPizzaDao pizzaDao;

    @Autowired
    public PizzaService(IPizzaDao pizzaDao) {
        this.pizzaDao = pizzaDao;
    }

    @Override
    public Long upsertPizza(PizzaDto pizzaDto) {
        return Objects.nonNull(pizzaDto.getId())
                ? pizzaDao.update(pizzaDto).getId()
                : pizzaDao.insert(pizzaDto).getId();
    }
}
