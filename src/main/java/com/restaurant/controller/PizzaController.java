package com.restaurant.controller;

import com.restaurant.service.IPizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaController {

    private final IPizzaService pizzaService;

    @Autowired
    public PizzaController(IPizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

}
