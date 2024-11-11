package org.amanverma.restaurant.mapper;

import org.amanverma.restaurant.dto.CustomerRequest;
import org.amanverma.restaurant.dto.CustomerResponse;
import org.amanverma.restaurant.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .address(request.address())
                .city(request.city())
                .pincode(request.pincode())
                .build();
    }

}
