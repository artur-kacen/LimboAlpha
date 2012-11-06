package com.limbo.app.pdf;

import java.io.IOException;
import java.util.regex.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.limbo.app.domain.Repair;
import com.limbo.app.domain.SystemUser;


public class RepairPDFGeneration {
	/** The original PDF file. */
	public static final String DATASHEET = "http://localhost:8080/LimboAlpha/pdf/template.pdf";

	
	
	public void fill(HttpServletRequest request, HttpServletResponse response, Repair repair, SystemUser currentUser, SystemUser creator) throws IOException, DocumentException {
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
		BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1257, false);
		
		//cb.addTemplate(page, 0, 0);
		
		cb.beginText();
		cb.setFontAndSize(bf, 12);
		
		cb.setTextMatrix(85, 723);
        cb.showText(repair.getId().toString());
        
        cb.setTextMatrix(138, 697);
        if (repair.getReceiptDate() != null)
        	cb.showText(repair.getReceiptDate().toString());
        
        cb.setTextMatrix(169, 671);
        if (repair.getRepairDate() != null)
        	cb.showText(repair.getRepairDate().toString());
		
		cb.setTextMatrix(82, 646);		
        cb.showText(creator.getName() + " " + creator.getSurname());
        
        cb.setTextMatrix(75, 595);
        cb.showText(repair.getClientFullName());
        
        cb.setTextMatrix(152, 570);
        cb.showText(repair.getClientMobileNumber());
        
        cb.setTextMatrix(170, 544);
        cb.showText(repair.getPhoneManufacturer() + " " + repair.getPhoneModel());
        
        cb.setTextMatrix(87, 518);
        if (repair.getWarrantyPeriod() != null)
        	cb.showText(Integer.toString(repair.getWarrantyPeriod()));
        
        cb.setTextMatrix(90, 493);
        cb.showText(repair.getImei());
        
        cb.setTextMatrix(106, 468);
        cb.showText(repair.getPhoneSecurityCode());
        
        cb.setTextMatrix(129, 442);
        if (repair.isPhone())
        	cb.showText("X");
        cb.setTextMatrix(179, 442);
        if (repair.getCharger());
        	cb.showText("X");
        cb.setTextMatrix(227, 442);	
        if (repair.isBattery()){
        	cb.showText("X");
        	cb.setTextMatrix(251, 442);
        	cb.showText(repair.getBaterySerialNumber());
        }
        cb.setTextMatrix(33, 400);
        Integer start = 400;
        String complain = repair.getComplains();
        Matcher matcher = Pattern.compile(".{1,150}+", Pattern.MULTILINE).matcher(complain);
        //complain.split("(?<=\\G.{110})");
       // for (String text: complain.split("(?<=\\G.{100})")){
        cb.setFontAndSize(bf, 9);
        while (matcher.find()){
        	cb.setTextMatrix(33, start);
        	cb.showText(matcher.group());
        	start-=12;
        }
        cb.setFontAndSize(bf, 12);

        cb.setTextMatrix(120, 173);
        if (repair.getPaymentAmount() != null){
        	String amount = repair.getPaymentAmount().toString();        
        	cb.showText(amount.substring(0, amount.length() - 2) + "," + amount.substring(amount.length() - 2) + " Ls");
        }
        	
        
        cb.setTextMatrix(257, 148);
        cb.showText(repair.getClientFullName());
        
        cb.setTextMatrix(345, 122);
        cb.showText(currentUser.getName() + "  " + currentUser.getSurname());
        // we tell the contentByte, we've finished drawing text
        cb.endText();
        cb.addTemplate(page, 0, 0);
		// Now do the usual addition of text atop the template
		//document.add(new Paragraph("Here some text added to the template2222"));
		// Etc, etc

		// Done!
		document.close();
		response.getOutputStream().close();

	}
}
