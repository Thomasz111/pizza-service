package com.restaurant.repositoriy;

import com.restaurant.model.PizzaDto;
import com.restaurant.model.PizzaDtoBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PizzaDaoTest {

    @InjectMocks
    private PizzaDao pizzaDao;

    private PizzaDto pizzaWithoutId;

    @BeforeEach
    public void setup() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("salmon");
        ingredients.add("chicken");

        pizzaWithoutId = PizzaDtoBuilder.create()
                .withName("name")
                .withSize(5.5f)
                .withPrice(BigDecimal.valueOf(10.5))
                .withIngredients(ingredients)
                .build();
    }

    @Test
    void insertTest() {
        PizzaDto pizzaDto = pizzaDao.insert(pizzaWithoutId);
        assertThat(pizzaDao.get(pizzaDto.getId()))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(pizzaWithoutId);
    }

    @Test
    void getAllPizzasTest() {
        pizzaDao.insert(pizzaWithoutId);
        pizzaDao.insert(pizzaWithoutId);
        pizzaDao.insert(pizzaWithoutId);

        assertThat(pizzaDao.getAllPizzas())
                .isNotNull()
                .hasSize(3);
    }
}