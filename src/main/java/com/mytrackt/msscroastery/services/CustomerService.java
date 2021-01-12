package com.mytrackt.msscroastery.services;

import com.mytrackt.msscroastery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);
}
