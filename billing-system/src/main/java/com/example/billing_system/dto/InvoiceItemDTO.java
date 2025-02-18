package com.example.billing_system.dto;


public class InvoiceItemDTO {
	//private Long id;
    private String productName;
    private int quantity;
    private double price;
    private Long productId;
    private double unitPrice;
    
    

	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	/*
	 * public String getName() { return name; } public void setName(String name) {
	 * this.name = name; }
	 */
	// Getters & Setters
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	/*
	 * public Long getId() { return id; } public void setId(Long id) { this.id = id;
	 * }
	 */
	
	
    
}


