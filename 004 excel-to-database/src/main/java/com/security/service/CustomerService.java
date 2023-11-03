package com.security.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.security.entity.customer;
import com.security.repo.CustomerRepository;


@Service
public class CustomerService {

	private String filePath="C:\\Users\\Sreenivas Bandaru\\Downloads\\CustomerDetails.xlsx";
	@Autowired
	private CustomerRepository repo;
	public void saveCustomerToDatabase(MultipartFile file) throws Exception {
		if(ExcelUploadService.isValidExcelFile(file)) {
			List<customer> customers=ExcelUploadService.getCustomerDataFromExcel(file.getInputStream());
			repo.saveAll(customers);
		}else throw new Exception("invalid file");
	}
	
	public List<customer> getCustomers(){
		return repo.findAll();
	}
	
	public String DataBaseToExcel() throws IOException {
		System.out.println(getCustomers());
		ExcelUploadService.dataBaseToExcel(getCustomers(),filePath);
		return filePath;
	}
	
}
