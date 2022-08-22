package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.Book;
import com.demo.repository.BookRepository;

@Service
public class BookService {

	
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> findAll(){
		return bookRepository.findAll();
	}
	
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	public Optional<Book> findById(long id) {
		
		
		return bookRepository.findById(id);
	}
	
	public List<Book> getBooklength(int length){
		return bookRepository.getBooklength(length);
	}
	
	
}
