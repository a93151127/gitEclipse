package com.martin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.martin.dao.User;
import com.martin.form.UserForm;
import com.martin.service.UserService;

@Controller
public class LoginController {
	
	
	@Autowired
	UserService userService;
	
	@GetMapping("/registerPage")
	public 	String registerPage() {
		System.out.println("inside login controller");
		return "register";
	}
	
	@GetMapping("/loginPage")
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("/register")
	public String register(@Valid UserForm userForm, BindingResult result) {
		
		String returnResult="register";
		
		List<FieldError> fieldErrors=new ArrayList<FieldError>();
		
		if(result.hasErrors()) {
			fieldErrors=result.getFieldErrors();
			if(!userForm.checkPassword()) {
				result.rejectValue("comfirmpassword","ERROR 0000","兩次密碼不一致");
			}
		}else {
			if(!userForm.checkPassword()) {
				result.rejectValue("comfirmpassword","ERROR 0000","兩次密碼不一致");
			}else {
				returnResult="redirect:loginPage";
			}
		}
		System.out.println("size:"+fieldErrors.size());
		if(StringUtils.equals(returnResult, "register")) {
			for(FieldError fieldError:fieldErrors) {
				System.out.println("fieldError:"+fieldError.getField()+"\tmessage:"+fieldError.getDefaultMessage()
									+"\tcode:"+fieldError.getCode());
			}
			return returnResult;
		}else {
			User user = userForm.convertToUser();
			userService.save(user);
			
			result.rejectValue("comfirmpassword","ERROR 0000","兩次密碼不一致");
			return returnResult;
		}
		
	}
	
}
