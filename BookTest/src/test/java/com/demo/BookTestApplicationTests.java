package com.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.dao.Book;
import com.demo.service.BookService;



@SpringBootTest
class BookTestApplicationTests {
	
	@Autowired
	private BookService bookService;

	@Test
	void contextLoads() {
		
		List<Book> bookList=bookService.getBooklength(5);
		
		System.out.println("size============="+bookList.size());
	}
	

}
