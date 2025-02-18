package com.example.billing_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.billing_system.entities.Product;
import com.example.billing_system.repository.ProductRepository;
import com.example.billing_system.services.ProductService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductRepository productRepository;
    
    
    
    @GetMapping("/list1")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    // Show all products
    @GetMapping("list")
    public String listProducts(Model model) {
    	
    	  //  model.addAttribute("products", productService.getAllProducts());
        List<Product> products = productService.getAllProducts();
       
        model.addAttribute("products", products);
        return "product/list"; // Redirects to products.html
    }

    // Show product form
    @GetMapping("/add")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/add"; // Redirects to product-form.html
    }

    // Handle product form submission
    @PostMapping("/add")
    public String saveProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products/list";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/edit";
    }
    
    // Handle form submission for update
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        productService.updateProduct(product);
        return "redirect:/products/list";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products/list";
    }
}

