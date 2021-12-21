package com.searchfight.search.secure.exception;

public class exceptionapi extends RuntimeException {


	public exceptionapi(String message) {
        super(message);
    }

    public exceptionapi(String message, Throwable cause) {
        super(message, cause);
    }
}