package com.example.billing_system.services;

import java.util.List;

import com.example.billing_system.entities.Customer;
import com.example.billing_system.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

   
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

	public Long countCustomers() {
		  return customerRepository.count();
	
	}
}


