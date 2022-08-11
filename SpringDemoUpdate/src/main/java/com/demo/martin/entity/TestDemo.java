package com.demo.martin.entity;

import org.springframework.beans.factory.annotation.Value;

public class TestDemo {
	
	private String bookName;
	
	private String bookDescript;
	
	public TestDemo() {	
		super();
	}
	
	public TestDemo(String bookName, String bookDescript) {
		super();
		this.bookName=bookName;
		this.bookDescript=bookDescript;
	}



	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookDescript() {
		return bookDescript;
	}

	public void setBookDescript(String bookDescript) {
		this.bookDescript = bookDescript;
	}
	
	
}
