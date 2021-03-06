package com.restaurant.repositoriy;

import com.restaurant.model.PizzaDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PizzaDao implements IPizzaDao {

    // TODO make database instead of mock
    private Map<Long, PizzaDto> pizzaMap = new HashMap<>();
    private Long lastId = 0L;

    @Override
    public PizzaDto update(PizzaDto pizzaDto) {
        return pizzaMap.replace(pizzaDto.getId(), pizzaDto);
    }

    @Override
    public PizzaDto insert(PizzaDto pizzaDto) {
        Long pizzaId = getNextId();
        pizzaDto.setId(pizzaId);
        pizzaMap.put(pizzaId, pizzaDto);
        return pizzaDto;
    }

    @Override
    public PizzaDto get(Long pizzaId) {
        return pizzaMap.get(pizzaId);
    }

    @Override
    public List<PizzaDto> getAllPizzas() {
        return new ArrayList<>(pizzaMap.values());
    }

    @Override
    public PizzaDto delete(Long pizzaId) {
        return pizzaMap.remove(pizzaId);
    }

    private Long getNextId() {
        return lastId += 1;
    }
}
