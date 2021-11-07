package com.wproject.livrariaapi.erro;

public class UserAlreadyExistsError extends RuntimeException{

	public UserAlreadyExistsError(String msg) {
		super(msg);
	}
	
	

}
