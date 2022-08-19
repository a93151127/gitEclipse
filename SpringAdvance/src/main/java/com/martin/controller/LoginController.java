package com.martin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public 	String registerPage(Model model) {
		System.out.println("inside login controller");
		model.addAttribute("userForm", new UserForm());
		return "register";
	}
	
	@GetMapping("/loginPage")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPost(@RequestParam String username,
            @RequestParam String password,
            HttpSession session) {
		User user = userService.findByUsernameAndPassword(username, password);
		
		if(user !=null) {
			session.setAttribute("user", user);
			return "index";
		}
		return "loginPage";
	}
	
	@PostMapping("/register")
	public String register(@Valid UserForm userForm, BindingResult result) {
		
		if(!userForm.checkPassword()) {
			result.rejectValue("comfirmpassword","ERROR 0000","兩次密碼不一致");
		}
		if(result.hasErrors()) {
			return "register";
		}
		User user = userForm.convertToUser();
		userService.save(user);
		
		/*
		 * 這邊實際上有執行一段
		 * model.addAttribute("userForm",userForm)
		 */
		
		return "redirect:/loginPage";
			
	}
	
	@GetMapping("/testException")
	public String testException() {
		System.out.println("inside testException");
		throw new RuntimeException("測試異常處理");
	}
	
}
