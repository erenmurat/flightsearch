package com.emirates.flightsearch.exception;

public class NotFoundException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8389190262510361700L;
	
	String message;
    public NotFoundException(String message) {
        this.message = message;
    }
}
