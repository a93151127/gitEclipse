package com.demo.martin.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.martin.entity.Book;
import com.demo.martin.repository.BookRepository;
import com.demo.martin.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	BookService bookservice;
	
	@Autowired
	BookRepository bookrepository;
	
	@GetMapping("/thymeBook")
	public String getBook() {
		return "books";
	}
	
	@GetMapping("/getOneBook/{id}")
	public String getOneBook(@PathVariable long id, Model model) {
		Optional<Book> bookOpt = bookservice.findOne(id);
		
		Book book=new Book();
		if(bookOpt.isPresent()) {
			book = bookOpt.get();
		}	
		
		model.addAttribute("book",book);
		return "book";
	}
}
