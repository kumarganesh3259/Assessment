package com.cwc.assessment.rafifuzz.exceptions.concrete;

public class UserNotFoundException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6726728806989476375L;

	public UserNotFoundException(){

    }

    public UserNotFoundException(String msg){
        super(msg);
    }
}
