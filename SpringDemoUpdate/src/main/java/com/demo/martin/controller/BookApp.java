package com.demo.martin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.martin.entity.Book;
import com.demo.martin.service.BookService;

@RestController
public class BookApp {
	
	@Autowired
	private BookService boodService;
	
	@GetMapping("/booksFromEntity")
	public List<Book> getAll(){
		
		return boodService.findAll();
	}
	
	
	//寫法也可以改為public String post(Book book)
	@PostMapping("/insertBook")
	public String post(Book book) {
		String result ="success to insert";
		
//		Book book=new Book();
//		book.setName(name);
//		book.setAuthor(author);
//		book.setDescription(description);
//		book.setStatus(status);
//		System.out.println("name===="+name);
//		System.out.println("author===="+author);
//		System.out.println("description===="+description);
//		System.out.println("status===="+status);
		
		try {
			boodService.save(book);
		}catch (Exception e) {
			result="fail to insert";
		}
		return result;
	}
	
	@PostMapping("/findOne")
	public Optional<Book> findOne(@RequestParam("Id") long id){
		
		return boodService.findOne(id);
	}
	
	@PutMapping("/update")
	public String update(@RequestParam("Id") long id,
			@RequestParam("name") String name,
			@RequestParam("author") String author,
			@RequestParam("description") String description,
			@RequestParam("status") int status) {
		String result="success to update";
		
		Book book=new Book();
		book.setId(id);
		book.setName(name);
		book.setAuthor(author);
		book.setDescription(description);
		book.setStatus(status);
		System.out.println("name===="+name);
		System.out.println("author===="+author);
		System.out.println("description===="+description);
		System.out.println("status===="+status);
		System.out.println("id===="+id);
		
		try {
			boodService.save(book);
		}catch (Exception e) {
			result="fail to update";
		}
		
		
		return result;
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		String result = boodService.delete(id);
		return result;
	}
	
	@PostMapping("/findByAuthor")
	public List<Book> findByAuthor(@RequestParam("author") String author){
		return boodService.findByAuthor(author);
	}
	
	@PostMapping("/findByAuthorAndStatus")
	public List<Book> findByAuthorAndStatus(String author, int status){
		return boodService.findyByAuthorAndStatus(author, status);
	}
	
	@PostMapping("/findByDesEndsWith")
	public List<Book> findByDescriptionEndsWith(String description){
		return boodService.findByDescriptionEndsWith(description);
	}
	
	@PostMapping("/findByDesContains")
	public List<Book> findByDescriptionContains(String description){
		return boodService.findByDescriptionContains(description);
	}
	
	@PostMapping("/findByLength")
	public List<Book> getBooklength(int len){
		return boodService.getBooklength(len);
	}
	
//	@PostMapping("/updateByJpa")
//	public int updateByJpa(int status, long Id) {
//		System.out.println("status====="+status);
//		System.out.println("Id====="+Id);
//		int result = boodService.updateByJpa(status, Id);
//		return result;
//	}
	
	@PostMapping("/deleteByJpa")
	public int deleteByJpa(long Id) {
		int result = boodService.deleteByJpa(Id);
		return result;
	}
	
	@PostMapping("/deleteUpdate")
	public int deleteAndUpdate(long Id, String status, long uId) {
		return boodService.deleteAndUpdate(Id, status, uId);
	}
}
