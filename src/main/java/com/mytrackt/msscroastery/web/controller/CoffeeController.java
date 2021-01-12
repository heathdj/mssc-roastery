package com.mytrackt.msscroastery.web.controller;

import com.mytrackt.msscroastery.services.CoffeeService;
import com.mytrackt.msscroastery.web.model.CoffeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/coffee")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    /**
     *
     * @return CoffeeDto
     */
    @GetMapping("/{coffeeId}")
    public ResponseEntity<CoffeeDto> getCoffee(@PathVariable("coffeeId") UUID coffeeId) {
        return new ResponseEntity<>(coffeeService.getCoffeeById(coffeeId), HttpStatus.OK);
    }

}
