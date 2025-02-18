package com.example.billing_system.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.billing_system.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	 @Query("SELECT p FROM Product p WHERE p.isDeleted = false")
	    List<Product> findAllActiveProducts();
}

