package com.martin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.martin.dao.Book;

public interface BookRepository extends JpaRepository<Book,Long>{
	
	Page<Book> findAll(Pageable pageable);
	
	//jpa會根據命名自動產生sql搜尋方法
	//這邊是用author欄位去尋找
	List<Book> findByAuthor(String author);
	
	//這邊是用author和status欄位搜尋
	List<Book> findByAuthorAndStatus(String author, int status);
	
	//description欄位以什麼結尾
	List<Book> findByDescriptionEndsWith(String des);
	
	//description欄位模糊比對
	List<Book> findByDescriptionContains(String des);
	
	//自定義查詢
	//hql語法查詢(Book為實體物件)
	//@Query("select b from Book b where length(b.name)>?1")
	//sql語法查詢
	@Query(value = "SELECT * FROM book where length(name)>?1",nativeQuery = true)
	List<Book> getBooklength(int length) ;
	
	@Modifying
	@Transactional
	@Query("update Book b set b.status = ?1 where id = ?2")
	//@Query(value = "update book set status = ?1 where id = ?2",nativeQuery = true)
	//@Modifying和@Transactional標籤都是DML操作必須的(修改資料,例如刪除或更新)
	int updateByJpa(String status, long id);
	
	@Modifying
	@Transactional
	@Query("delete from Book b where b.id = ?1")
	int deleteByJpa(long id);
	
	
}
