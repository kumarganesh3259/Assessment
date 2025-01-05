package com.cwc.assessment.rafifuzz.exceptions.concrete;

public class EmailAlreadyExistsException extends RuntimeException {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -7660062050461531215L;

	public EmailAlreadyExistsException(String msg){
	        super(msg);
	    }

}
