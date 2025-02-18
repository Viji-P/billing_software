package com.example.billing_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.billing_system.entities.Invoice;
import com.example.billing_system.services.CustomerService;
import com.example.billing_system.services.InvoiceService;
import com.example.billing_system.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

	/*
	 * // Show all invoices
	 * 
	 * @GetMapping("/get") public String listInvoices(Model model) { List<Invoice>
	 * invoices = invoiceService.getAllInvoices(); model.addAttribute("invoices",
	 * invoices); return "invoices"; // Redirects to invoices.html }
	 * 
	 * // Show invoice creation form
	 * 
	 * @GetMapping("/new") public String showInvoiceForm(Model model) {
	 * model.addAttribute("invoice", new Invoice()); model.addAttribute("customers",
	 * customerService.getAllCustomers()); model.addAttribute("products",
	 * productService.getAllProducts()); return "billing/invoice-view"; // Redirects
	 * to invoice-form.html }
	 * 
	 * // Handle invoice submission
	 * 
	 * @PostMapping("/save") public String saveInvoice(@ModelAttribute Invoice
	 * invoice) { invoiceService.addInvoice(invoice); return "redirect:/invoices"; }
	 */
    
    
    @GetMapping("/invoice/{id}")
    public String viewInvoice(@PathVariable Long id, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        model.addAttribute("invoice", invoice);
        return "billing/invoice-view";
    }

    @PostMapping("/generate")
    public String generateInvoice(@ModelAttribute Invoice invoice) {
        invoiceService.createInvoice(invoice);
        return "redirect:/billings/invoice/" + invoice.getId();
    }
}

