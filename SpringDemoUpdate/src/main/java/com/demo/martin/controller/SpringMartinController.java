package com.demo.martin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringMartinController {
	
	@RequestMapping("/")
	public String helloWorld() {
		return "hello world my friend hello hello";
	}
}
