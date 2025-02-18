package com.example.billing_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.billing_system.entities.Customer;
import com.example.billing_system.services.CustomerService;

import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Show all customers
    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        
      
        model.addAttribute("customers", customers);
        return "customer/list"; // Redirects to list.html
    }

    // Show customer registration form
    @GetMapping("/add")
    public String showCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/add"; // Redirects to customer.html
    }

    // Handle new customer form submission
    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerService.addCustomer(customer);
        return "redirect:/customers/list";
    }
}

