package com.bootcamp.java.client.service.exception;



public class InvalidClientDocumentTypeException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidClientDocumentTypeException() {
		super("This type of client document is invalid");
	}
}
