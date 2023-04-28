package com.Merlin.eshop.repository;

import com.Merlin.eshop.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByEmail(String email);

    Customer findByMobile(String mobile);
}
