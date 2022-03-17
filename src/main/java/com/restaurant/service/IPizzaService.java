package com.restaurant.service;


import com.restaurant.model.PizzaDto;

import java.util.List;

public interface IPizzaService {

    Long upsertPizza(PizzaDto pizzaDto);

    PizzaDto getPizza(Long pizzaId);

    List<PizzaDto> list();

}
