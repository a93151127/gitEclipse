package com.martin.util;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.martin.dao.Book;
import com.martin.dto.BookDTO;

public class CustomBeanUtil {
	
	
	
	public static String[] nullProperty(Object source) {
		//先把source做包裝
		BeanWrapper beanWrapper =new BeanWrapperImpl(source);
		//將包裝做成陣列
		PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
		List<String> nullPropertyNames = new ArrayList<String>();
		
		
		for(PropertyDescriptor pd:pds) {
			//取得屬性的名稱
			String propertyName=pd.getName();
			//判斷該屬性名稱的值是否為null
			if(beanWrapper.getPropertyValue(propertyName) == null) {
				nullPropertyNames.add(propertyName);
			}
		}
		//將list轉換成陣列
		return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
	}
}
