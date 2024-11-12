package org.amanverma.restaurant.service;

import org.amanverma.restaurant.dto.CustomerRequest;
import org.amanverma.restaurant.dto.CustomerResponse;
import org.amanverma.restaurant.dto.LoginRequest;
import org.amanverma.restaurant.entity.Customer;
import org.amanverma.restaurant.helper.EncryptionService;
import org.amanverma.restaurant.helper.JWTHelper;
import org.amanverma.restaurant.mapper.CustomerMapper;
import org.amanverma.restaurant.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService{

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String createCustomer(CustomerRequest request) {
        
        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        repo.save(customer);
        return "Customer Created Successfully";
    }

    public Customer getCustomer(String email){
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }


    public String login(LoginRequest request) {
        Customer customer = getCustomer(request.email());
        if(!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }

        return jwtHelper.generateToken(request.email());
    }

    public String updateCustomerByEmail(CustomerRequest request) {
        Customer customer = repo.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("No customer found with email: " + request.email()));

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setAddress(request.address());
        customer.setCity(request.city());
        customer.setPincode(request.pincode());

        repo.save(customer);
        return "Customer updated successfully";
    }

    public void deleteCustomerByEmail(String email) {
        Customer customer = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No customer found with email: " + email));

        repo.delete(customer);
    }

}
