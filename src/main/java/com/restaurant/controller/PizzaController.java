package com.restaurant.controller;

import com.restaurant.model.PizzaDto;
import com.restaurant.service.IPizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaController {

    private final IPizzaService pizzaService;

    @Autowired
    public PizzaController(IPizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping("/pizza/upsert")
    public Long upsertPizza(@RequestBody PizzaDto pizzaDto) {
        return pizzaService.upsertPizza(pizzaDto);
    }

}
