package org.amanverma.restaurant.repo;

import org.amanverma.restaurant.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
