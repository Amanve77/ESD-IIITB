package org.amanverma.restaurant.service;

import org.amanverma.restaurant.dto.CustomerRequest;
import org.amanverma.restaurant.dto.CustomerResponse;
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
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        Customer savedCustomer = repo.save(customer);
        return new CustomerResponse(
                savedCustomer.getFirstName(),
                savedCustomer.getLastName(),
                savedCustomer.getEmail()
        );
    }
}
