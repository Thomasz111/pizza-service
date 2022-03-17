package com.restaurant.repositoriy;


import com.restaurant.model.PizzaDto;

public interface IPizzaDao {

    PizzaDto update(PizzaDto pizzaDto);

    PizzaDto insert(PizzaDto pizzaDto);

}
