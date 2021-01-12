package com.mytrackt.msscroastery.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeDto {

    private UUID id;
    private String coffeeName;
    private String coffeeStyle;
    private Long upc;
}
