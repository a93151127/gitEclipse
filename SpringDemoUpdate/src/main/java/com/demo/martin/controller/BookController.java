package com.demo.martin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	//原始寫法
	//public String getBook(Model model,@RequestParam(defaultValue="1" ) int page, @RequestParam(defaultValue="5" ) int size) {
	public String getBook(Model model,@PageableDefault(size=5,sort={"id"},direction=Sort.Direction.DESC) Pageable pageable	) {
		/*原始寫法
		Sort sort= Sort.by(Sort.Direction.DESC,"id");
		if(page<0) {
			page=0;
		}
		Pageable pageable= PageRequest.of(page, size, sort);
		Page<Book> page1=bookservice.findAllByPage(pageable);
		*/
		//TODAY IS A GOOD DAY
		//新寫法
		Page<Book> page1=bookservice.findAllByPage(pageable);
		model.addAttribute("page", page1);
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
	
	@GetMapping("/jumpToInput")
	public String addBook(Model model) {		
		model.addAttribute("book",new Book());
		return "input";
	}
	@PostMapping("/addNewBook")
	public String postNewBook(final RedirectAttributes attri, Book book) {
		Book book1=bookservice.save(book);
		
		//使頁面產生新增成功或失敗的訊息
		/*
		 * 
		 * 
		 * 這種寫法失敗是因為實際上請求過程是這樣
		 * post---->redirect----->get
		 * 期間經過了兩個request,但model只能在一個request裡面產生
		 * 因此信息會遺失
		if(book1 !=null) {
			model.addAttribute("message",book1.getName());
		}
		*/
		if(book1 !=null) {
			attri.addFlashAttribute("message",book1.getName());
		}
		
		/*寫法一
		List<Book> bookList=bookservice.findAll();
		model.addAttribute("books", bookList);
		return "books";
		*/
		//寫法2
		return "redirect:/thymeBook";
	}

	
	@GetMapping("/editBook/{id}")
	public String jumpToEdit(@PathVariable long id, Model model) {	
		Optional<Book> bookOpt= bookservice.findOne(id);
		Book book=new Book();
		if(bookOpt.isPresent()) {
			book=bookOpt.get();
		}
		model.addAttribute("book", book);
		return "input";
	}
	

}
