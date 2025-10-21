package com.daw.service.exceptions;


public class TareaNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9207277199654582L;
	
	
	public TareaNotFoundException(String message) {
		super(message);
	}

}
