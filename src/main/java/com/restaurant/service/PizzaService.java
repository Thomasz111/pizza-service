package com.restaurant.service;

import com.restaurant.repositoriy.IPizzaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService implements IPizzaService {

    private final IPizzaDao pizzaDao;

    @Autowired
    public PizzaService(IPizzaDao pizzaDao) {
        this.pizzaDao = pizzaDao;
    }

}
