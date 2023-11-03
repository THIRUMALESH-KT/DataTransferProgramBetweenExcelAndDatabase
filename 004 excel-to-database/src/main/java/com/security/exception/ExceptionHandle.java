package com.security.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler(Exception.class)
	public Map<String,Object> handleException(Exception ex){
		Map<String,Object> map=new HashMap<>();
		map.put("result", "failed");
		map.put("message"+" [ "+ex.getClass()+" ]", ex.getLocalizedMessage());
		map.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
		return map;
	}
}
