package com.martin.resource;

public class InvalidErrorResource {
	private String message;
	private Object error;
	
	public InvalidErrorResource(String message, Object error) {
		super();
		this.message = message;
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public Object getError() {
		return error;
	}
	
	
}
