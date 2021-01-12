package com.mytrackt.msscroastery.services;

import com.mytrackt.msscroastery.web.model.CoffeeDto;

import java.util.UUID;

public interface CoffeeService {
    CoffeeDto getCoffeeById(UUID coffeeId);
}
