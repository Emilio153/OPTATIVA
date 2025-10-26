package com.daw.services.Exceptions;

public class PokemonNotFoundExceptions extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5065698640744918049L;
	
	public PokemonNotFoundExceptions(String message) {
		super(message);
	}
}
