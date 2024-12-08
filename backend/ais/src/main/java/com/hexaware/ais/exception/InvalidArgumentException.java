package com.hexaware.ais.exception;


/**
 * @Author: Kishlay Kumar
 * Class: InvalidArgumentException
 * Description: Custom exception for handling invalid or illegal arguments
 */
public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}