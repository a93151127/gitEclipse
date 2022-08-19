package com.martin.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.martin.controller.BookController;

/*
 * @ControllerAdvice
 * 可以視為Controller的攔截器
 * 所有經過的Controller都要先進來這裡
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest request, Exception e ) throws Exception {
		logger.error("Request URL: {}", request.getRequestURL(),e.getMessage());
		
		/*
		 * 如果我們其他地方有使用annotation去指定exception的話會跳到指定的錯誤頁面,否則才會執行下面的那段程式碼
		 */
		if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null) {
			throw e;
		}
		
		ModelAndView mav =new ModelAndView();
		mav.addObject("url",request.getRequestURL());
		mav.addObject("exception",e);
		mav.setViewName("error/error");
		
		return mav;
	}
}
