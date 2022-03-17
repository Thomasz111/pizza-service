package com.restaurant.controller;

import com.restaurant.model.PizzaDto;
import com.restaurant.service.IPizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/pizza/get")
    public PizzaDto getPizza(@RequestParam Long pizzaId) {
        return pizzaService.getPizza(pizzaId);
    }

    @GetMapping("/pizza/list")
    public List<PizzaDto> getPizza() {
        return pizzaService.list();
    }


}
