package com.victor.workshopmongo.exception;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String id) {
		super("O ID " + id + " NÃO FOI ENCONTRADO");
	}

}
