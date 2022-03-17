package com.restaurant.model;

import java.math.BigDecimal;
import java.util.List;

public final class PizzaDtoBuilder {
    private Long id;
    private String name;
    private BigDecimal price;
    private Float size;
    private List<String> ingredients;

    private PizzaDtoBuilder() {
    }

    public static PizzaDtoBuilder create() {
        return new PizzaDtoBuilder();
    }

    public PizzaDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PizzaDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PizzaDtoBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public PizzaDtoBuilder withSize(Float size) {
        this.size = size;
        return this;
    }

    public PizzaDtoBuilder withIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public PizzaDto build() {
        PizzaDto pizzaDto = new PizzaDto();
        pizzaDto.setId(id);
        pizzaDto.setName(name);
        pizzaDto.setPrice(price);
        pizzaDto.setSize(size);
        pizzaDto.setIngredients(ingredients);
        return pizzaDto;
    }
}
