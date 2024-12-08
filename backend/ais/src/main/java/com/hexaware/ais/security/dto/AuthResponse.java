package com.hexaware.ais.security.dto;


/*
 * @Author: Kishlay Kumar
 * Class: AuthResponse
 * Description: Data Transfer Object for login response containing JWT token.
 */
public class AuthResponse {

    /******************************************* Attributes *******************************************/

    private String token;

    /******************************************* Constructors *******************************************/

    public AuthResponse(String token) {

        super();
        this.token = token;
    }

    /******************************************* Getters and Setters *******************************************/

    // Getter and Setter for token
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}