package com.example.billing_system.config;

import com.example.billing_system.dto.InvoiceDTO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PdfGenerator {

    public void export(InvoiceDTO invoice, HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        try {
			PdfWriter.getInstance(document, response.getOutputStream());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph title = new Paragraph("Invoice", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph("Customer: " + invoice.getCustomerName()));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3);
        table.addCell("Product");
        table.addCell("Quantity");
        table.addCell("Price");

        for (var item : invoice.getItems()) {
            table.addCell(item.getProductName());
            table.addCell(String.valueOf(item.getQuantity()));
            table.addCell(String.valueOf(item.getPrice()));
        }

        document.add(table);
        document.add(new Paragraph("Total Amount: $" + invoice.getTotalAmount()));
        document.close();
    }
}
