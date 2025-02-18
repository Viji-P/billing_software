package com.example.billing_system.services;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.billing_system.dto.InvoiceDTO;
import com.example.billing_system.dto.InvoiceItemDTO;
import com.example.billing_system.entities.Customer;
import com.example.billing_system.entities.Invoice;
import com.example.billing_system.entities.InvoiceItem;
import com.example.billing_system.entities.Product;
import com.example.billing_system.repository.CustomerRepository;
import com.example.billing_system.repository.InvoiceItemRepository;
import com.example.billing_system.repository.InvoiceRepository;
import com.example.billing_system.repository.ProductRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillingService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public InvoiceDTO generateInvoice(Long customerId,List<InvoiceItemDTO> selectedItems) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice = invoiceRepository.save(invoice); // Save to generate ID

        double totalAmount = 0;
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        List<InvoiceItemDTO> itemDTOs = new ArrayList<>();

        for (InvoiceItemDTO selectedItem : selectedItems) {
            Product product = productRepository.findById(selectedItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found!"));

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoice(invoice);
            invoiceItem.setProduct(product);
            invoiceItem.setQuantity(selectedItem.getQuantity());
            invoiceItem.setPrice(product.getPrice());

            totalAmount += product.getPrice() * selectedItem.getQuantity();
            invoiceItems.add(invoiceItem);

            // Prepare DTO
            InvoiceItemDTO itemDTO = new InvoiceItemDTO();
            itemDTO.setProductName(product.getName());
            itemDTO.setQuantity(selectedItem.getQuantity());
            itemDTO.setPrice(product.getPrice());
            itemDTO.setUnitPrice(product.getPrice());

            itemDTOs.add(itemDTO);
        }

        invoiceItemRepository.saveAll(invoiceItems); // Save all invoice items
        InvoiceDTO invoiceDTO = new InvoiceDTO();
      //  Product product = new Product();
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setCustomerName(customer.getName());
        invoiceDTO.setItems(itemDTOs);
       // invoiceDTO.setUnitPrice(product.getPrice());

        invoiceDTO.setTotalAmount(totalAmount);
        invoiceDTO.setInvoiceDate(LocalDate.now());
        if (invoiceDTO.getDueDate() == null) {
        	invoiceDTO.setDueDate(invoiceDTO.getInvoiceDate().plusDays(7)); // Default due date 7 days later
        }
        if (invoiceDTO.getInvoiceNumber() == null || invoiceDTO.getInvoiceNumber().isEmpty()) {
        	invoiceDTO.setInvoiceNumber(generateInvoiceNumber()); // Generate invoice number
        }
        

        return invoiceDTO;
    }
    

    private String generateInvoiceNumber() {
		// TODO Auto-generated method stub
    	 return "INV-" + System.currentTimeMillis();
	}

	InvoiceDTO dto = new InvoiceDTO();
	public byte[] generateInvoicePdf(Long id) {
		// TODO Auto-generated method stub
		   try {
		        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		        Document document = new Document();
		        PdfWriter.getInstance(document, outputStream);
		        document.open();

		        // Add invoice details
		        document.add(new Paragraph("Invoice ID: " + id));
		        document.add(new Paragraph("Customer: " + dto.getCustomerName()));  // Replace with actual data
		        document.add(new Paragraph("Date: " + LocalDate.now()));

		        document.close();
		        return outputStream.toByteArray();
		    } catch (Exception e) {
		        throw new RuntimeException("Error generating PDF", e);
		    }
	
	}

}

