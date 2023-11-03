package com.security.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.security.service.CustomerService;

import jakarta.annotation.Resource;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService service;
	@PostMapping("/upload") 
	public Map<String,Object>upload(@RequestParam("file") MultipartFile file) throws Exception{
		service.saveCustomerToDatabase(file);
		Map<String,Object> map=new HashMap<>();
		map.put("message", "uploaded sucefully");
		return map;
				
	}
	@GetMapping("/get")
	public Map<String,Object> get(){
		Map<String,Object> map=new HashMap<>();
		map.put("message", service.getCustomers());
		return map;
	
	}
	@GetMapping("/download")
	public Map<String,Object> download()throws IOException{
		Map<String,Object> map=new HashMap<>();
		map.put("result", "sucess");
		map.put("message", service.DataBaseToExcel());
		map.put("status", HttpStatus.OK);
		return map;
					
    }
}
