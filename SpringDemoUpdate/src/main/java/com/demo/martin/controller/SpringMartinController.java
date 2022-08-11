package com.demo.martin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SpringMartinController {
	
	@RequestMapping("/")
	public String helloWorld() {
		System.out.println("today is a good day");
		return "books";
	}
}
