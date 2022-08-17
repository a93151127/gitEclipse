package com.martin.controller;

import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.martin.dao.Book;
import com.martin.exception.BookNotFoundException;
import com.martin.service.BookService;

@Controller
public class BookController {
	
	private final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookservice;
	
	@GetMapping("/{id}")
	public String getBook(@PathVariable long id,Model model) {
		Book book = new Book();
		Optional<Book> bookOpt = bookservice.findOne(id);
		if(bookOpt.isPresent()) {
			book=bookOpt.get();
		}else {
			throw new BookNotFoundException("此書不存在");
		}
		model.addAttribute("book",book);
		return "book";
	}
	
	/*
	 * 下面的方法只可以處理發生在這個controller的異常
	 * 其他的controller例如LoginController發生的異常就無法處理
	 * 若要讓全部的Controller都可以處理異常只能使用"全域異常處理"
	 * ControllerExceptionHandler.java
	 */
	//@ExceptionHandler({BookNotFoundException.class,SQLException.class})
	//直接處理全部異常
//	@ExceptionHandler(Exception.class)
//	public ModelAndView handleException(HttpServletRequest request, Exception e ) throws Exception {
//		logger.error("Reqiest URL: {}", request.getRequestURL(),e.getMessage());
//		
//		/*
//		 * 如果我們其他地方有使用annotation去指定exception的話會跳到指定的錯誤頁面,否則才會執行下面的那段程式碼
//		 */
//		if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null) {
//			throw e;
//		}
//		
//		ModelAndView mav =new ModelAndView();
//		mav.addObject("url",request.getRequestURL());
//		mav.addObject("exception",e);
//		mav.setViewName("error/error");
//		
//		return mav;
//	}
}
