package com.userservice.exception;

public class ResourceNotFoundEXception extends RuntimeException {
	public ResourceNotFoundEXception() {
		// TODO Auto-generated constructor stub
	}
	public ResourceNotFoundEXception(String msg){
		super("Resource not found exception");
	}
	
}
