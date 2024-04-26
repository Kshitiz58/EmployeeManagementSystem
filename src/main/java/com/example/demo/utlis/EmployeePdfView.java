package com.example.demo.utlis;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.model.Employee;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmployeePdfView extends AbstractPdfView {

	
	@Override
	protected void buildPdfMetadata (Map<String, Object> model, Document document, HttpServletRequest request) {
		HeaderFooter header = new HeaderFooter(new Phrase("Employee pdf view"),false);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);
		
		HeaderFooter footer = new HeaderFooter(new Phrase(new Date()+" ©kshitiz Page "), true);
		footer.setAlignment(Element.ALIGN_CENTER);
		document.setFooter(footer);
		
	}
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		response.addHeader("Content-Disposition", "Attachment;filename=Employee.pdf");
		
		@SuppressWarnings("unchecked")
		List<Employee> list = (List<Employee>) model.get("eList");
		
		Font titleFont = new Font(Font.TIMES_ROMAN, 20, Font.BOLD, Color.BLACK);
		Paragraph title = new Paragraph("Employee Data", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingBefore(20.0f);
		title.setSpacingAfter(25.0f);
		
		document.add(title);
		
		Font tableHead = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK);
		PdfPTable table = new PdfPTable(7);
		table.setSpacingAfter(Element.ALIGN_CENTER);
		table.addCell(new Phrase("Id", tableHead));
		table.addCell(new Phrase("F Name", tableHead));
		table.addCell(new Phrase("L Name", tableHead));
		table.addCell(new Phrase("Gender", tableHead));
		table.addCell(new Phrase("Phone", tableHead));
		table.addCell(new Phrase("Email", tableHead));
		table.addCell(new Phrase("Company", tableHead));
		
		
		for (Employee spec : list) {
			table.addCell(String.valueOf(spec.getId()));
			table.addCell(spec.getFname());
			table.addCell(spec.getLname());
			table.addCell(spec.getGender());
			table.addCell(spec.getPhone());
			table.addCell(spec.getEmail());
			table.addCell(spec.getCompany());
			
			
		}
		document.add(table);
		
	}

}
