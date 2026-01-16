package com.softmax.testhotwax.repository;

import com.softmax.testhotwax.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
