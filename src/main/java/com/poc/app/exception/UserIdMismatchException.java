package com.poc.app.exception;

public class UserIdMismatchException extends  RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserIdMismatchException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public UserIdMismatchException()
    {
        super();
    }
}
