package com.hexaware.ais.security.dto;

import jakarta.validation.constraints.NotBlank;


/*
 * @Author: Kishlay Kumar
 * Class: AuthRequest
 * Description: Data Transfer Object for login request.
 */
public class AuthRequest {

    /******************************************* Attributes *******************************************/

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    /******************************************* Getters and Setters *******************************************/

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}