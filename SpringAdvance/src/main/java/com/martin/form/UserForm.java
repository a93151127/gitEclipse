package com.martin.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import com.martin.dao.User;
import com.martin.form.convert.FormConvert;

public class UserForm {
	
	private final static String PHONE_REG="^09[0-9]{8}$";
	
	private long id;
	@NotBlank
	private String username;
	@NotBlank
	@Length(min=6,message="馬丁預設密碼至少需要六位數以上")
	private String password;
	//@Pattern(regexp="PHONE_REG")
	private String phone;
	@Email
	private String email;
	@NotBlank
	private String comfirmpassword;
	
	public UserForm() {
		super();
	}
	
	public boolean checkPassword() {
		System.out.println("inside checkPassword");
		String cp=this.comfirmpassword;
		String c=this.password;
		System.out.println("comfirmpassword:"+cp);
		System.out.println("password:"+c);
		System.out.println("boolean:"+this.comfirmpassword.equals(this.password));
		
		return this.comfirmpassword.equals(this.password);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComfirmpassword() {
		return comfirmpassword;
	}

	public void setComfirmpassword(String comfirmpassword) {
		this.comfirmpassword = comfirmpassword;
	}
	
	public User convertToUser() {
		User user =new User();
		try {
			user = new UserFormConvert().convertFor(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public class UserFormConvert implements FormConvert<UserForm, User>{

		@Override
		public User convertFor(UserForm s) {
			User user = new User();
			BeanUtils.copyProperties(s, user);
			return user;
		}

	}
}

