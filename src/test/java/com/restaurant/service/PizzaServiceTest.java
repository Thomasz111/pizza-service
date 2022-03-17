package com.restaurant.service;

import com.restaurant.model.PizzaDto;
import com.restaurant.model.PizzaDtoBuilder;
import com.restaurant.repositoriy.PizzaDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

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
    void updatePizzaNotExistingTest() {
        when(pizzaDao.update(pizzaWithId))
                .thenReturn(null);

        assertThat(pizzaService.upsertPizza(pizzaWithId))
                .isNotNull()
                .isEqualTo(0L);
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

    @ParameterizedTest
    @MethodSource("deleteTestParams")
    void deleteTest(PizzaDto pizzaDto, Boolean expected) {
        when(pizzaDao.delete(PIZZA_ID))
                .thenReturn(pizzaDto);

        assertThat(pizzaService.delete(PIZZA_ID))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> deleteTestParams() {
        return Stream.of(
            Arguments.of(PizzaDtoBuilder.create().build(), true),
            Arguments.of(null, false)
        );
    }

}