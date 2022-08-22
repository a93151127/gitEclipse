package com.demo.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import com.demo.convertForm.BookConvert;
import com.demo.dao.Book;

public class BookForm {
	
	@NotBlank
	@Length(min=6,message="馬丁預設至少需要六個字以上")
	private String name;
	@NotBlank
	private String author;
	@NotBlank
	private String description;
	private int status;
	
	

	
	public BookForm() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public Book convertBookForm() {
		Book book = new Book();
		book = new BookConvertImpl().convertBookForm(this);
		return book;
	}
	
	
	private class BookConvertImpl implements BookConvert{

		@Override
		public Book convertBookForm(BookForm bookForm) {
			Book book = new Book();
			BeanUtils.copyProperties(bookForm, book);
			return book;
		}
		
	}
}
