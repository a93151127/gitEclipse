package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.dao.Book;

public interface BookRepository extends JpaRepository<Book,Long>{
	
		//sql語法查詢
		@Query(value = "SELECT * FROM book where length(name)>?1",nativeQuery = true)
		List<Book> getBooklength(int length) ;
		
}
