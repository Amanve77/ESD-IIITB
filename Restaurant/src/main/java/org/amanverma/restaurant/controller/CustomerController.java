package org.amanverma.restaurant.controller;

import org.amanverma.restaurant.dto.CustomerRequest;
import org.amanverma.restaurant.dto.CustomerResponse;
import org.amanverma.restaurant.dto.LoginRequest;
import org.amanverma.restaurant.helper.JWTHelper;
import org.amanverma.restaurant.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.amanverma.restaurant.validation.ValidationGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")

public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    @Autowired
    private final JWTHelper jwtHelper;

    @PostMapping()
    @Validated(ValidationGroups.CreateGroup.class)
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/{email}")
    @Validated(ValidationGroups.UpdateGroup.class)
     public ResponseEntity<String> updateCustomer(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody @Valid CustomerRequest request, @PathVariable String email) {
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;

        if (!jwtHelper.validateToken(jwtToken, email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(customerService.updateCustomerByEmail(request));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomer(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String email) {
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;

        if (!jwtHelper.validateToken(jwtToken, email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        customerService.deleteCustomerByEmail(email);
        return ResponseEntity.ok("Customer deleted successfully");
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> retrieveCustomer(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String email) {
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;

        if (!jwtHelper.validateToken(jwtToken, email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        CustomerResponse customer = customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(customer);
    }
}
