package com.example.billing_system.services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.billing_system.entities.Product;
import com.example.billing_system.repository.InvoiceItemRepository;
import com.example.billing_system.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private InvoiceItemRepository invoiceItemRepository;
    
  //  private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllActiveProducts();
    }

   
    
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
    	 Product product = productRepository.findById(id)
    		        .orElseThrow(() -> new RuntimeException("Product not found!"));

    		    product.setDeleted(true); // Mark product as deleted
    		    productRepository.save(product);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);  // Save the updated product
    }

    public Long countProducts() {
        return productRepository.count();
    }
}


