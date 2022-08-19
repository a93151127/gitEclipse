package com.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.dao.Book;
import com.demo.service.BookService;

@Controller
public class BookController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/findAll")
	public String findAll() {
		List<Book> bookList=bookService.findAll();
		
		if(!bookList.isEmpty()) {
			for(Book book:bookList) {
				log.info("book={}:",book);
				System.out.println(book);
			}
		}
		return "books";
	}
}
