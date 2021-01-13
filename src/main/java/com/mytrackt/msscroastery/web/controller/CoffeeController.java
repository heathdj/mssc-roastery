package com.mytrackt.msscroastery.web.controller;

import com.mytrackt.msscroastery.services.CoffeeService;
import com.mytrackt.msscroastery.web.model.CoffeeDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Create a new Coffee
     * @param coffeeDto
     * @return
     */
    @PostMapping
    public ResponseEntity handlePost(@RequestBody CoffeeDto coffeeDto) {
        CoffeeDto saveDto = coffeeService.saveNewCoffee(coffeeDto);

        HttpHeaders headers = new HttpHeaders();
        // TODO: Add hostname to URL
        headers.add("Location", "/api/v1/coffee/" + saveDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{coffeeId}")
    public ResponseEntity handleUpdate(@PathVariable("coffeeId") UUID coffeeId, @RequestBody CoffeeDto coffeeDto) {
        coffeeService.updateCoffee(coffeeId, coffeeDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
