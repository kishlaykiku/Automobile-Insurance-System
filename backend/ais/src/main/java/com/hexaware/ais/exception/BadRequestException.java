package com.hexaware.ais.exception;


/*
 * @Author: Kishlay Kumar
 * Class: BadRequestException
 * Description: Custom exception for handling bad requests
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {

        super(message);
    }
}