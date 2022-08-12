package com.demo.martin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.martin.entity.Book;
import com.demo.martin.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAll(){
		System.out.println("inside service");
		return bookRepository.findAll();
	}
	
	public Book save(Book book){
		//save的物件如果有id且資料庫裡有該筆,會進行更新的動作,若沒有id,則會進行新增
		return bookRepository.save(book);
	}
	
	public Optional<Book> findOne(long id) {
		Optional<Book> book=null;
		try {
			book = bookRepository.findById(id);
		}catch (Exception e) {
			
		}
		return book;
	}
	
	public String delete(long id) {
		String result = "success to delete";
		
		try {
			bookRepository.deleteById(id);
		}catch (Exception e) {
			result = "fail to delete";
		}
		
		return result;
	}
	
	public List<Book> findByAuthor(String author){
		return bookRepository.findByAuthor(author);
	}
	
	public List<Book> findyByAuthorAndStatus(String author, int status){
		return bookRepository.findByAuthorAndStatus(author, status);
	}
	
	public List<Book> findByDescriptionEndsWith(String des){
		return bookRepository.findByDescriptionEndsWith(des);
	}
	
	public List<Book> findByDescriptionContains(String des){
		return bookRepository.findByDescriptionContains(des);
	}
}
