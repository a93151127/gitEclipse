package com.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.dao.Book;
import com.demo.exception.BookNotFoundException;
import com.demo.form.BookForm;
import com.demo.service.BookService;

@Controller
public class BookController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/findById/{id}")
	public String findById(@PathVariable long id,Model model) {

		
		Book book = new Book();
		Optional<Book> bookOpt = bookService.findById(id);
		System.out.println("isPresent======"+bookOpt.isPresent());
		
		if(bookOpt.isPresent()) {
			book=bookOpt.get();
		}else {
			System.out.println("inside false");
			throw new BookNotFoundException("此書不存在");
		}
		
		System.out.println("111111111111");
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book);
		
		model.addAttribute("books",bookList);
		
		return "books";
	}
	
	@GetMapping("/findAll")
	public String findAll(Model model) {
		List<Book> bookList=bookService.findAll();
		if(!bookList.isEmpty()) {
			for(Book book:bookList) {
				log.info("book={}:",book);
				System.out.println(book);
			}
		}
		
		model.addAttribute("books",bookList);
	
		return "books";
	}
	
	@GetMapping("/jumpToInsert")
	public String jumpToInsert(Model model) {
		model.addAttribute("bookForm",new BookForm());
		return "insert";
	}
	
	@PostMapping("/insertUpdateBook")
//	public String insertUpdateBook(@RequestParam String name, @RequestParam String author, @RequestParam String description,
//			@RequestParam int status) {
	public String insertUpdateBook(@Valid BookForm bookForm, BindingResult result, final RedirectAttributes attri) {
		
		Book book = bookService.save(bookForm.convertBookForm());
		
		
		if(!result.hasErrors()&& book!=null) {
			attri.addFlashAttribute("message", book.getName());
		}else {
			List<FieldError> errorList = result.getFieldErrors();
			for(FieldError error:errorList) {
				System.out.println(error.getDefaultMessage());
			}
			return "insert";
		}
				
		return "redirect:/findAll";
	}
	
	@GetMapping("/testException")
	public String testException() {
		throw new RuntimeException("測試異常處理");
	}
}
