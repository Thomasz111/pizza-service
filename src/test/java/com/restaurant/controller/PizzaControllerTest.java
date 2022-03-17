package com.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.model.PizzaDto;
import com.restaurant.model.PizzaDtoBuilder;
import com.restaurant.service.PizzaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class PizzaControllerTest {

    private final Long PIZZA_ID = 5L;

    @Mock
    private PizzaService pizzaService;

    @InjectMocks
    private PizzaController pizzaController;

    private MockMvc mockMvc;
    private PizzaDto pizzaDto;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(pizzaController).build();
        pizzaDto = PizzaDtoBuilder.create()
                .withName("name")
                .withId(PIZZA_ID)
                .build();
    }

    @Test
    public void getPizzaTest() throws Exception {
        when(pizzaService.getPizza(PIZZA_ID)).thenReturn(pizzaDto);

        String json = mockMvc.perform(get("/pizza/get")
                .param("pizzaId", PIZZA_ID.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        PizzaDto result = new ObjectMapper().readValue(json, PizzaDto.class);
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(pizzaDto);
    }

    @Test
    public void deletePizzaTest() throws Exception {
        when(pizzaService.delete(PIZZA_ID)).thenReturn(true);

        String json = mockMvc.perform(delete("/pizza/delete")
                .param("pizzaId", PIZZA_ID.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Boolean result = new ObjectMapper().readValue(json, Boolean.class);
        assertThat(result).isTrue();
    }

}