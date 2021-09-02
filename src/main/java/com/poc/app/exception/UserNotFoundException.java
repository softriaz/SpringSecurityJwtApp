package com.poc.app.exception;

public class UserNotFoundException extends  RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message, Throwable cause)
    {
        super(message,cause);
    }

    public UserNotFoundException()
    {
        super();
    }
}
