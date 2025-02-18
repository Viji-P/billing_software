package com.example.billing_system.services;

import java.util.List;

import com.example.billing_system.entities.Invoice;
import com.example.billing_system.repository.InvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    public Long countInvoices() {
        return invoiceRepository.count();
    }

	/*
	 * public List<Invoice> getRecentInvoices() { return
	 * invoiceRepository.findTop5ByOrderByDateDesc(); }
	 */
    
    public Invoice createInvoice(Invoice invoice) {
        invoice.setInvoiceNumber(generateInvoiceNumber());
        invoice.setDueDate(invoice.getInvoiceDate().plusDays(7)); // Default 7-day due date
        return invoiceRepository.save(invoice);
    }

    private String generateInvoiceNumber() {
        return "INV-" + System.currentTimeMillis();
    }
}


