package com.example.billing_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.billing_system.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
