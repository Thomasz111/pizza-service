package com.restaurant.repositoriy;


import com.restaurant.model.PizzaDto;

import java.util.List;

public interface IPizzaDao {

    PizzaDto update(PizzaDto pizzaDto);

    PizzaDto insert(PizzaDto pizzaDto);

    PizzaDto get(Long pizzaId);

    List<PizzaDto> getAllPizzas();

}
