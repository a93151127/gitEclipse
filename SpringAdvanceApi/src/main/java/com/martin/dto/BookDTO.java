package com.martin.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import com.martin.dao.Book;
import com.martin.util.CustomBeanUtil;

public class BookDTO{
	@NotBlank
	private String name;
	@Length(min=3)
	private String author;
	
	private int status;
	@Length(max=10)
	private String description;

	public BookDTO() {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void convert(Book book) {
		new ConvertImpl().convert(this, book);
	}
	
	public Book convert() {
		return new ConvertImpl().convert(this);
	}
	
	private class ConvertImpl implements Convert<BookDTO,Book>{

		@Override
		public void convert(BookDTO bookDto, Book book) {
			//取得屬性值為空的屬性名稱陣列
			String[] nullPropertyNames = CustomBeanUtil.nullProperty(bookDto);
			//放入後排除屬性為空的屬性
			BeanUtils.copyProperties(bookDto, book, nullPropertyNames);
			
		}
		public Book convert(BookDTO bookDto) {
			
			Book book= new Book();
			BeanUtils.copyProperties(bookDto, book);
			return book;
		}
		
	}
	
}
