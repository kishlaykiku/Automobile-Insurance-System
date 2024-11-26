package com.hexaware.ais.exception;


/*
 * @Author: Kishlay Kumar
 * Class: ResourceNotFoundException
 * Description: Custom exception for handling resource not found
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {

        super(message);
    }
}