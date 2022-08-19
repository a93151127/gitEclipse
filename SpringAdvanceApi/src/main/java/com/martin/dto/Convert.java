package com.martin.dto;

import com.martin.dao.Book;

public interface Convert <S,T>{
	void convert(S s,T t);

}
