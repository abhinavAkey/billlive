package com.beatus.billlive.validation.exception;

public class BillliveClientValidationException extends Exception{

	/**
	 * @author vakey15
	 * This is a exception that is thrown when there is a bad data in the request parameters
	 */
	private static final long serialVersionUID = 1L;

	public BillliveClientValidationException(String message) {
        super(message);
    }

    public BillliveClientValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}