package com.martin.handler;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.martin.exception.BookNotFoundException;
import com.martin.exception.InvalidRequestException;
import com.martin.resource.ErrorResource;
import com.martin.resource.FieldResource;
import com.martin.resource.InvalidErrorResource;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(BookNotFoundException.class)
	@ResponseBody
	public ResponseEntity<?> handleNotFoundException(BookNotFoundException e){
		ErrorResource error = new ErrorResource(e.getMessage());	
		return new ResponseEntity<Object>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	@ResponseBody
	public ResponseEntity<?> HandleInvalidRequest(InvalidRequestException e){
		List<FieldResource> fieldResourceList = new ArrayList<FieldResource>();
		//Controller會把錯誤訊息包在BindingResult然後再放入InvalidRequestException這個class
		Errors errors =e.getErrors();
		//使用getFieldErrors把欄位錯誤題取出來
		List<FieldError> fieldErrors = errors.getFieldErrors();
		for( FieldError fe:fieldErrors) {
			//getObjaectName實體對象名字,getField欄位,getCode錯誤碼,getDefaultMessage默認訊息
			FieldResource fieldResource = new FieldResource(fe.getObjectName(), fe.getField(),
															fe.getCode(), fe.getDefaultMessage());
		
			fieldResourceList.add(fieldResource);
		}
			
		
		InvalidErrorResource ier= new InvalidErrorResource(e.getMessage(),fieldResourceList);
		return new ResponseEntity<Object>(ier,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<?> handleException(Exception e){
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
	}	
}