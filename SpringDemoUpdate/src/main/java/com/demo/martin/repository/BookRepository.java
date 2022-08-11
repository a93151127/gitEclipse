package com.demo.martin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.martin.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long>{
	
}
