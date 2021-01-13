package com.mytrackt.msscroastery.services;

import com.mytrackt.msscroastery.web.model.CoffeeDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CoffeeServiceImpl implements CoffeeService {
    @Override
    public CoffeeDto getCoffeeById(UUID coffeeId) {
        return CoffeeDto.builder().id(UUID.randomUUID())
                .coffeeName("Blue Mountain Coffee")
                .coffeeStyle("Medium Roast")
                .build();
    }

    @Override
    public CoffeeDto saveNewCoffee(CoffeeDto coffeeDto) {
        return CoffeeDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateCoffee(UUID coffeeId, CoffeeDto coffeeDto) {
        //TODO impl
    }
}
