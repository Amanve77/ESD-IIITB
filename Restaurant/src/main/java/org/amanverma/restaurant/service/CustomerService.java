package org.amanverma.restaurant.service;

import org.amanverma.restaurant.dto.CustomerRequest;
import org.amanverma.restaurant.dto.CustomerResponse;
import org.amanverma.restaurant.dto.LoginRequest;
import org.amanverma.restaurant.entity.Customer;
import org.amanverma.restaurant.mapper.CustomerMapper;
import org.amanverma.restaurant.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService{

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
    public String login(LoginRequest loginRequest) {
        Customer customer = repo.findByEmail(loginRequest.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!customer.getPassword().equals(loginRequest.password())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return "User logged in successfully";
    }
}
