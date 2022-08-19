package com.martin.controller;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestApi {
	
	private final org.slf4j.Logger log=LoggerFactory.getLogger(LogTestApi.class);
	
	@GetMapping("/log")
	public String log() {
		String name="martin";
		String email="a93151127@gmail.com";
//		log.info("info---log");
//		log.warn("warn---log");
//		log.error("error---log");
//		log.debug("debug---log");
//		log.trace("trace---log");
		
		/*
		 * 可以使用此方法印出相對應的變數
		 * 盡量不要使用+等拼接字串
		 */
//		log.info("name: {} , email :{}",name,email);
		log.info("inside LogTestApi=======");
		
		return "testlog";
	}
}
