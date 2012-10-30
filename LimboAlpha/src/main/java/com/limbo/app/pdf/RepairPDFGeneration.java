package com.limbo.app.pdf;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.limbo.app.domain.Repair;
import com.limbo.app.domain.SystemUser;


public class RepairPDFGeneration {
	/** The original PDF file. */
	public static final String DATASHEET = "http://localhost:8080/LimboAlpha/pdf/template.pdf";

	
	
	public void fill(HttpServletRequest request, HttpServletResponse response, Repair repair, SystemUser user) throws IOException, DocumentException {
		PdfReader reader;
		response.setHeader("Content-disposition", "attachment; filename="+  repair.getId()  +".pdf");
		reader = new PdfReader(DATASHEET);
		// Setup a new PDF Document
		Document document = new Document();

		PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		document.newPage();

		// Here's the key part. Let's turn the template in to
		// usable PDF object
		PdfImportedPage page = writer.getImportedPage(reader, 1);
		
		// Now, add it to the blank PDF document we've opened
		PdfContentByte cb = writer.getDirectContent();
		BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI, false);
		//cb.addTemplate(page, 0, 0);
		
		cb.beginText();
		cb.setFontAndSize(bf, 12);
		
		cb.setTextMatrix(82, 725);
        cb.showText(repair.getId().toString());
        
        cb.setTextMatrix(138, 700);
        if (repair.getReceiptDate() != null)
        	cb.showText(repair.getReceiptDate().toString());
        
        cb.setTextMatrix(169, 674);
        if (repair.getRepairDate() != null)
        	cb.showText(repair.getRepairDate().toString());
		
		cb.setTextMatrix(82, 649);
        cb.showText(user.getName() + " " + user.getSurname());
        
        cb.setTextMatrix(75, 598);
        cb.showText(repair.getClientFullName());
        
        cb.setTextMatrix(170, 547);
        cb.showText(repair.getPhoneManufacturer());
        
        cb.setTextMatrix(87, 521);
        if (repair.getWarrantyPeriod() != 0)
        	cb.showText(Integer.toString(repair.getWarrantyPeriod()));
        
        cb.setTextMatrix(90, 496);
        cb.showText(repair.getImei());
        
        cb.setTextMatrix(106, 472);
        cb.showText(repair.getPhoneSecurityCode());
        
        cb.setTextMatrix(109, 445);
        cb.showText("MT.");
        
        cb.setTextMatrix(33, 403);
        cb.showText(repair.getComplains());

        cb.setTextMatrix(120, 165);
        if (repair.getPaymentAmount() != null)
        	cb.showText(repair.getPaymentAmount().toString());

        // we tell the contentByte, we've finished drawing text
        cb.endText();
        cb.addTemplate(page, 0, 0);
		// Now do the usual addition of text atop the template
		//document.add(new Paragraph("Here some text added to the template2222"));
		// Etc, etc

		// Done!
		document.close();

	}
}
