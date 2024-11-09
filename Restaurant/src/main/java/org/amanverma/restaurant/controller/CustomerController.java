package org.amanverma.restaurant.controller;

import org.amanverma.restaurant.dto.CustomerRequest;
import org.amanverma.restaurant.dto.CustomerResponse;
import org.amanverma.restaurant.dto.LoginRequest;
import org.amanverma.restaurant.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")

public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(customerService.login(loginRequest));
    }

}
