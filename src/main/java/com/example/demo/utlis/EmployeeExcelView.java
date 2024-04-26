package com.example.demo.utlis;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.example.demo.model.Employee;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmployeeExcelView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Content-Disposition", "attachment;filename = employee.xls");

		@SuppressWarnings("unchecked")
		List<Employee> list = (List<Employee>) model.get("elist");

		Sheet sheet = workbook.createSheet("Employee");

		setHead(sheet);

		setBody(sheet, list);

	}

	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("First Name");
		row.createCell(2).setCellValue("Last Name");
		row.createCell(3).setCellValue("Gender");
		row.createCell(4).setCellValue("Phone");
		row.createCell(5).setCellValue("Email");
		row.createCell(6).setCellValue("Company");
		row.createCell(7).setCellValue("Salary");
		

	}
	private void setBody(Sheet sheet, List<Employee> list) {
		int rowNum = 1;
		for (Employee spec : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getId());
			row.createCell(1).setCellValue(spec.getFname());
			row.createCell(2).setCellValue(spec.getLname());
			row.createCell(3).setCellValue(spec.getGender());
			row.createCell(4).setCellValue(spec.getPhone());
			row.createCell(5).setCellValue(spec.getEmail());
			row.createCell(6).setCellValue(spec.getCompany());
			row.createCell(7).setCellValue(spec.getSalary());
		}
		
	}

}
