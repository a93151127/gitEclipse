package com.demo.martin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.martin.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long>{
	
	//jpa會根據命名自動產生sql搜尋方法
	//這邊是用author欄位去尋找
	List<Book> findByAuthor(String author);
	
	//這邊是用author和status欄位搜尋
	List<Book> findByAuthorAndStatus(String author, int status);
	
	//description欄位以什麼結尾
	List<Book> findByDescriptionEndsWith(String des);
	
	//description欄位模糊比對
	List<Book> findByDescriptionContains(String des);
	
}
