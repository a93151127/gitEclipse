package com.demo.martin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.martin.entity.TestDemo;

@RestController

public class SpringMartinController {
	
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
	
	@GetMapping("ymlClassBook")
	public Object getYmlClassBook() {
		Map<String,Object> map = new HashMap<>();
		TestDemo testDemo = new TestDemo(bookName,bookDescript);
		
		map.put("name", testDemo.getBookName());
		map.put("name1", bookName);
		map.put("descript", testDemo.getBookDescript());
		map.put("descript1", bookDescript);
		map.put("object", testDemo);
		
		return map;
	}
}
