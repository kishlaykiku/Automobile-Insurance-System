package com.hexaware.ais.exception;

import java.time.LocalDateTime;


/*
 * @Author: Kishlay Kumar
 * Class: ErrorResponse
 * Description: This class is a model for error response
 */
public class ErrorResponse {

    /******************************************* Attributes *******************************************/
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    /******************************************* Constructors *******************************************/

    // Default Constructor
    public ErrorResponse() {

        super();
    }

    // Constructor to map the error response
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {

        super();

        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /******************************************* Getters and Setters *******************************************/

    // Getter and Setter for timestamp
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Getter and Setter for status
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    // Getter and Setter for error
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }

    // Getter and Setter for message
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter and Setter for path
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}