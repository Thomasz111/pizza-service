package com.restaurant.service;

import com.restaurant.model.PizzaDto;
import com.restaurant.model.PizzaDtoBuilder;
import com.restaurant.repositoriy.PizzaDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PizzaServiceTest {

    @Mock
    private PizzaDao pizzaDao;

    @InjectMocks
    private PizzaService pizzaService;

    private final Long PIZZA_ID = 5L;
    private PizzaDto pizzaWithoutId;
    private PizzaDto pizzaWithId;

    @BeforeEach
    public void setup() {
        pizzaWithoutId = PizzaDtoBuilder.create()
                .withName("name")
                .build();
        pizzaWithId = PizzaDtoBuilder.create()
                .withName("name")
                .withId(PIZZA_ID)
                .build();
    }

    @Test
    void insertPizzaTest() {
        when(pizzaDao.insert(pizzaWithoutId))
                .thenReturn(pizzaWithId);

        assertThat(pizzaService.upsertPizza(pizzaWithoutId))
                .isNotNull();
        verify(pizzaDao).insert(pizzaWithoutId);
    }

    @Test
    void updatePizzaTest() {
        when(pizzaDao.update(pizzaWithId))
                .thenReturn(pizzaWithId);

        assertThat(pizzaService.upsertPizza(pizzaWithId))
                .isNotNull()
                .isEqualTo(PIZZA_ID);
        verify(pizzaDao).update(pizzaWithId);
    }

    @Test
    void getPizzaTest() {
        when(pizzaDao.get(PIZZA_ID))
                .thenReturn(pizzaWithId);

        assertThat(pizzaService.getPizza(PIZZA_ID))
                .usingRecursiveComparison()
                .isEqualTo(pizzaWithId);
    }
}