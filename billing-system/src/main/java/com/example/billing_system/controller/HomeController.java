package com.example.billing_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.billing_system.services.CustomerService;
import com.example.billing_system.services.InvoiceService;
import com.example.billing_system.services.ProductService;

import org.springframework.ui.Model;

@Controller
public class HomeController {
	
	  @Autowired
	    private CustomerService customerService;

	    @Autowired
	    private ProductService productService;

	    @Autowired
	    private InvoiceService invoiceService;
	
    @GetMapping("/home")
    public String home(Model model) {
    	model.addAttribute("totalCustomers", customerService.countCustomers());
        model.addAttribute("totalProducts", productService.countProducts());
        model.addAttribute("totalInvoices", invoiceService.countInvoices());
       // model.addAttribute("recentInvoices", invoiceService.getRecentInvoices());
        
        // Get authentication details
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Check if user is authenticated
        String username = (auth != null && auth.getPrincipal() instanceof User) 
                          ? ((User) auth.getPrincipal()).getUsername() 
                          : null;
        
        model.addAttribute("username", username);
        return "home";
    }
}



