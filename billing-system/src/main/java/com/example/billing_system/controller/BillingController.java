package com.example.billing_system.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.billing_system.dto.InvoiceDTO;
import com.example.billing_system.dto.InvoiceItemDTO;
import com.example.billing_system.entities.Customer;
import com.example.billing_system.entities.Invoice;
import com.example.billing_system.entities.Product;
import com.example.billing_system.services.BillingService;
import com.example.billing_system.services.CustomerService;
import com.example.billing_system.services.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/billings")
public class BillingController {

    @Autowired
    private BillingService billingService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ProductService productService;

    // Show invoice form
    @GetMapping("/generate")
    public String showBillingForm(Model model) {
    	List<Customer> customers = customerService.getAllCustomers(); 
    	List<Product> products = productService.getAllProducts();
    	
        model.addAttribute("customers", customers);
        model.addAttribute("products",products);
        return "billing/billing-form"; // Redirects to billing-form.html
    }

    // Handle invoice generation
   /* @PostMapping("/invoice")
    public String generateBill(@RequestParam Long customerId, Model model) {
    	if (customerId == null) {
            throw new RuntimeException("Customer ID is missing!");
        }
        InvoiceDTO invoice = billingService.generateInvoice(customerId);
        model.addAttribute("invoice", invoice);
        return "billing/invoice-view"; // Redirects to invoice-view.html
    }*/
    @PostMapping("/invoice")
    public String generateBill(@RequestParam Long customerId,
    		 @RequestParam String productData, Model model) {
        System.out.println("Received customerId: " + customerId); // Debug log
        System.out.println("Received customerId: " + productData);


        if (customerId == null || productData.isEmpty() ) {
            throw new RuntimeException("Customer ID, Products, and Quantities are required!");
        }
        
        List<InvoiceItemDTO> selectedItems;
        try {
            selectedItems = new ObjectMapper().readValue(productData, new TypeReference<List<InvoiceItemDTO>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error parsing product data", e);
        }
        InvoiceDTO invoice = billingService.generateInvoice(customerId,selectedItems);
        model.addAttribute("invoice", invoice);

        return "billing/invoice-view"; // Redirects to invoice-view.html
    }
    
    @GetMapping("/invoice/pdf/{id}")
    public void downloadInvoicePdf(@PathVariable Long id, HttpServletResponse response) throws IOException {
        byte[] pdfData = billingService.generateInvoicePdf(id);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=invoice_" + id + ".pdf");
        response.getOutputStream().write(pdfData);
        response.getOutputStream().flush();
    }
}



