package com.example.billing_system.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;

public class InvoiceDTO {
    private Long id;
    private String customerName;
    private List<InvoiceItemDTO> items;
    private double totalAmount;
    private LocalDate invoiceDate;
    private String invoiceNumber;
    private LocalDate dueDate;
    private double unitPrice;
   
    public LocalDate getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	
	

    public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	// Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public List<InvoiceItemDTO> getItems() { return items; }
    public void setItems(List<InvoiceItemDTO> items) { this.items = items; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}
