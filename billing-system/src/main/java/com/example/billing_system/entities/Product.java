package com.example.billing_system.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;
    
    @Column(name="price")
    private double price;
    
    @Column(name="description")
    private String description;
    
    @Column(name="quantity")
    private int quantity;
    
    private boolean isDeleted = false;
    
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/*
	 * @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval
	 * = true) private List<InvoiceItem> invoiceItems;
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	/*
	 * public List<InvoiceItem> getInvoiceItems() { return invoiceItems; }
	 * 
	 * public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
	 * this.invoiceItems = invoiceItems; }
	 */
	
	

	
}
