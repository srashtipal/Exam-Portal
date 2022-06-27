package com.exam.exception;

public class UserNotFoundException extends Exception {
	
	public UserNotFoundException() {
		super("User with this Username is already there in DB!! try with another username");
	}
	
	public UserNotFoundException(String msg) {
		super(msg);
	}

}
