package com.demo.martin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.martin.entity.Book;
import com.demo.martin.entity.TestDemo;
import com.demo.martin.service.BookService;

@RestController

public class SpringMartinController {
	
//	@Autowired
//	private TestDemo testDemo;
	
	@Value("${book.name}")
	private String bookName;
	
	@Value("${book.description}")
	private String bookDescript;
	
	@RequestMapping("/")
	@ResponseBody
	public String helloWorld() {
		System.out.println("today is a good day");
		return "books";
	}
	
	@GetMapping("/baooks/{bookId}")
	public Object getOneBook(@PathVariable("bookId") long bid) {
		System.out.println("bid===="+bid);
		
		Map<String,Object> map = new HashMap<>();
		map.put("name","martin");
		map.put("age","37");
		map.put("bookId",bid);
		
		return map;
	}
	
	@GetMapping("/books/{name:[a-z]+}")
	public Object getName(@PathVariable("name") String name) {
		
		Map<String,Object> map =new HashMap<>();
		Map<String,Object> inListMap =new HashMap<>();
		Map<String,Object> inListMap2 =new HashMap<>();
		
		inListMap.put("language","english");
		inListMap.put("year","10");
		
		inListMap2.put("language","japan");
		inListMap2.put("year","5");
		
		List<Map<String,Object>> intList = new ArrayList<>();
		intList.add(inListMap);
		intList.add(inListMap2);
	
		map.put("name", name);
		map.put("list", intList);
		
		return map;
	}
	
	
	@PostMapping("/testPost")
	public Object getPostBook(@RequestParam("name") String bookName) {
		Map<String,Object> map = new HashMap<>();
		map.put("name",bookName);
		map.put("age","37");
		
		return map;
	}
	
	@GetMapping("/getPage")
	public Object getPage(@RequestParam("page") int page,@RequestParam("size") int size) {
		Map<String,Object> map = new HashMap<>();
		
		map.put("page",page);
		map.put("size",size);
		
		return map;
	}
	
	@GetMapping("/ymlBook")
	public Object getYmlBook() {
		Map<String,Object> map = new HashMap<>();
		
		map.put("name",bookName);
		map.put("desciription",bookDescript);
		
		return map;
	}
	
	@GetMapping("/ymlClassBook")
	public javax.sql.DataSource datasource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/book")
                .username("root")
                .password("root")
                .build();
    }
	
	
	
//	@GetMapping("/ymlObjectBook")
//	public Object getYmlObjectBook() {
//		return testDemo;
//	}
}
