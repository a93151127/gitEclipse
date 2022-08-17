package com.martin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martin.dao.Book;
import com.martin.exception.BookNotFoundException;
import com.martin.service.BookService;
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
		
		return new ResponseEntity<List<Book>>(bookList,HttpStatus.OK);
	}
	@GetMapping("/books/{id}")
	public ResponseEntity<?> findOneBook(@PathVariable long id){
		Book book=new Book();
		Optional<Book> bookOpt=bookservice.findOne(id);
		if(bookOpt.isPresent()) {
			book=bookOpt.get();
		}
		return new ResponseEntity<Book>(book,HttpStatus.OK);
	}
	
	@PostMapping("/addBook")
	public ResponseEntity<?> addNewBook(Book book){
		Book book1=bookservice.save(book);
		return new ResponseEntity<Book>(book1,HttpStatus.OK);
	}
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<?> updateBook(@PathVariable long id,Book book){
		Optional<Book> currentBookOpt=bookservice.findOne(id);
		Book book1=new Book();
		
		if(currentBookOpt.isPresent()) {
			Book currentBook=currentBookOpt.get();
			BeanUtils.copyProperties(book, currentBook);
			book1 = bookservice.save(book);
			
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
