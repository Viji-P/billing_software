package com.example.billing_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.billing_system.entities.InvoiceItem;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
	
	@Query("SELECT COUNT(i) FROM InvoiceItem i WHERE i.product.id = :productId")
	long countByProductId(@Param("productId") Long productId);

}

