package com.martin.form.convert;

public interface FormConvert<S,T> {
	T convertFor(S s);
}
