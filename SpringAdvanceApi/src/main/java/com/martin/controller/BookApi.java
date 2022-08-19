package com.martin.controller;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.martin.dao.Book;
import com.martin.dto.BookDTO;
import com.martin.exception.BookNotFoundException;
import com.martin.exception.InvalidRequestException;
import com.martin.service.BookService;
import com.martin.util.CustomBeanUtil;
/*
 * 因為我們這邊除了回應書籍
 * 還要多加一個http狀態
 * 所以不用Book或者List<Book>
 * 而是使用ResponseEntity<?>
 * 
 */
@RestController
public class BookApi {
	@Autowired
	BookService bookservice;
	
	@GetMapping("/books")
	public ResponseEntity<?> getAllBooks(){
		List<Book> bookList= bookservice.findAll();
		if(bookList.isEmpty()) {
			throw new BookNotFoundException("查不到任何書籍");
		}
		
		return new ResponseEntity<List<Book>>(bookList,HttpStatus.OK);
	}
	@GetMapping("/books/{id}")
	public ResponseEntity<?> findOneBook(@PathVariable long id){
		Book book=new Book();
		Optional<Book> bookOpt=bookservice.findOne(id);
		if(bookOpt.isPresent()) {
			book=bookOpt.get();
		}else {
			throw new BookNotFoundException(String.format("book by id %s not found", id));
		}
		return new ResponseEntity<Book>(book,HttpStatus.OK);
	}
	
	@PostMapping("/addBook")
	public ResponseEntity<?> addNewBook(@Valid @RequestBody Book book, BindingResult result){
		
		if(result.hasErrors()) {
			throw new InvalidRequestException("invalid request",result);
		}
		Book book1=bookservice.save(book);
		return new ResponseEntity<Book>(book1,HttpStatus.OK);
	}
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<?> updateBook(@PathVariable long id,@Valid @RequestBody BookDTO bookDto, BindingResult result){
		if(result.hasErrors()) {
			throw new InvalidRequestException("invalid request",result);
		}		
		Optional<Book> currentBookOpt=bookservice.findOne(id);
		Book book1=new Book();
		
		if(currentBookOpt.isPresent()) {
			Book currentBook=currentBookOpt.get();
			
			book1 = bookservice.save(bookDto.convert());
			
			return new ResponseEntity<Book>(book1, HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable long id){
		bookservice.delete(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
