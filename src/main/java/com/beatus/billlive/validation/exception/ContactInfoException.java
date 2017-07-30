package com.beatus.billlive.validation.exception;

public class ContactInfoException extends Exception {

	/**
	 * @author vakey15
	 * This is a exception that is thrown when there is a bad data in the request parameters
	 */
	private static final long serialVersionUID = 1L;

	public ContactInfoException(String message) {
        super(message);
    }

    public ContactInfoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
