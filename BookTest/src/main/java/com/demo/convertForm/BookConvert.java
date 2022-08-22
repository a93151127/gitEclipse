package com.demo.convertForm;

import com.demo.dao.Book;
import com.demo.form.BookForm;

public interface BookConvert {
	
	public Book convertBookForm(BookForm bookForm);
}
