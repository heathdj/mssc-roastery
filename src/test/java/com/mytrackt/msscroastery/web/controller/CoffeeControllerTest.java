package com.mytrackt.msscroastery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytrackt.msscroastery.services.CoffeeService;
import com.mytrackt.msscroastery.web.model.CoffeeDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



/**
 * Created in Intellij User: heath Date: 1/12/2021
 */

@RunWith(SpringRunner.class)
@WebMvcTest(CoffeeController.class)
public class CoffeeControllerTest {

    @MockBean
    CoffeeService coffeeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CoffeeDto validCoffee;

    @Before
    public void setUp() {
        validCoffee = CoffeeDto.builder().id(UUID.randomUUID())
                .coffeeName("coffee1")
                .coffeeStyle("MEDIUM_ROAST")
                .upc(12345678912L)
                .build();
    }

    @Test
    public void getCoffee() throws Exception {
        given(coffeeService.getCoffeeById(any(UUID.class))).willReturn(validCoffee);

        mockMvc.perform(get("/api/v1/coffee/" + validCoffee.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validCoffee.getId().toString())))
                .andExpect(jsonPath("$.coffeeName", is("coffee1")));
    }

    @Test
    public void handlePost() throws Exception {
        //given
        CoffeeDto coffeeDto = validCoffee;
        coffeeDto.setId(null);
        CoffeeDto savedDto = coffeeDto.builder().id(UUID.randomUUID()).coffeeName("New coffee").build();
        String coffeeDtoJson = objectMapper.writeValueAsString(coffeeDto);

        given(coffeeService.saveNewCoffee(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/coffee/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(coffeeDtoJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void handleUpdate() throws Exception {
        //given
        CoffeeDto coffeeDto = validCoffee;
        String coffeeDtoJson = objectMapper.writeValueAsString(coffeeDto);

        //when
        mockMvc.perform(put("/api/v1/coffee/" + validCoffee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(coffeeDtoJson))
                .andExpect(status().isNoContent());

        then(coffeeService).should().updateCoffee(any(), any());

    }

}
