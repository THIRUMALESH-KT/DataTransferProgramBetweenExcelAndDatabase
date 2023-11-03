package com.security.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.security.entity.customer;

@Service
public class ExcelUploadService {

	public static boolean isValidExcelFile(MultipartFile file) {
		String  contentType=file.getContentType();
		System.out.println(contentType);
		if(contentType.equals("application/vnd.ms-excel"))return true;
		else return false;
	}
	public static List<customer> getCustomerDataFromExcel(InputStream inputStream) throws IOException{
		List<customer> customer=new ArrayList<>();
		HSSFWorkbook workbook=new HSSFWorkbook(inputStream);
	System.out.println(workbook.getNumberOfSheets());
		HSSFSheet sheet=workbook.getSheetAt(0);
		int rowIndex=0;
		for(Row row:sheet) {
			if((rowIndex==0)) {
				rowIndex++;
				continue;
			}
			Iterator<Cell>cellIterator=row.iterator();
			int cellIndex=0;
			customer customer2=new customer();
			while(cellIterator.hasNext()) {
				Cell cell=cellIterator.next();
				switch(cellIndex) {
				case 0 ->customer2.setId((long) cell.getNumericCellValue());
				
				case 1 ->customer2.setFirstName(cell.getStringCellValue());
				case 2 ->customer2.setLastName(cell.getStringCellValue());
				case 3 ->customer2.setCountry(cell.getStringCellValue());
				case 4 ->customer2.setMobile((long) cell.getNumericCellValue());
				default ->{
					
				}
				}
				cellIndex++;
			}
			customer.add(customer2);
		}
		return customer;
	}
	
public static String sheet1="productList";
	
	public static void dataBaseToExcel(List<customer> list,String filePath)  {
		
		//for Create workBook
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheetName=workbook.createSheet(sheet1);

		
	
		 String[] headers={"id","firstname","lastname","country","mobile"};

			//for create sheet
			Row row=sheetName.createRow(1);
			for(int i=0;i<headers.length ;i++) {
				Cell cell=row.createCell(i);
				cell.setCellValue(headers[i]);
			}
			
			//Value rows
			int rowIndex=2;
			for(customer pe:list) {
				Row dataRow=sheetName.createRow(rowIndex++);
				
				
				
				dataRow.createCell(0).setCellValue(pe.getId());
				
				dataRow.createCell(1).setCellValue(pe.getFirstName());
				
				dataRow.createCell(2).setCellValue(pe.getLastName());
				
				dataRow.createCell(3).setCellValue(pe.getCountry());
				dataRow.createCell(4).setCellValue(pe.getMobile());
				
			}
			
			try (FileOutputStream fos = new FileOutputStream(filePath)) {
			    workbook.write(fos);
			    System.out.println("Excel file created successfully at: " + filePath);
			} catch (IOException e) {
			    e.printStackTrace();
			    System.err.println("Error creating the Excel file: " + e.getMessage());
			}
	}
}
