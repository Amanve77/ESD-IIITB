package org.amanverma.restaurant.controller;

import org.amanverma.restaurant.dto.CustomerRequest;
import org.amanverma.restaurant.dto.CustomerResponse;
import org.amanverma.restaurant.dto.LoginRequest;
import org.amanverma.restaurant.helper.JWTHelper;
import org.amanverma.restaurant.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")

public class CustomerController {
    private final CustomerService customerService;
    private final JWTHelper jwtHelper;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

}
