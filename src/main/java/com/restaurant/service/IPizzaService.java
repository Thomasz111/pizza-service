package com.restaurant.service;


import com.restaurant.model.PizzaDto;

public interface IPizzaService {

    Long upsertPizza(PizzaDto pizzaDto);

    PizzaDto getPizza(Long pizzaId);

}
