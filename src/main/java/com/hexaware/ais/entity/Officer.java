package com.hexaware.ais.entity;

import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/*
 * @Author: Kishlay Kumar
 * Class: Officer Entity
 * Description: This class is used to represent the Officer entity
 * Map: It is mapped to the `officer` table in the database
 */
@Entity
@Table(name = "officer")
public class Officer {

    /******************************************* Attributes *******************************************/

    @Id
    @Column(name = "officer_id", nullable = false, unique = true)
    private String officerId;

    @NotBlank(message = "Name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Role is required")
    @Column(name = "role", nullable = false)
    private String role;

    @PrePersist
    protected void generateId() {

        if (this.officerId == null) {

            this.officerId = UUID.randomUUID().toString();
        }
    }

    /******************************************* Getters and Setters *******************************************/

    // Getter and Setter for officerId
    public String getOfficerId() {
        return officerId;
    }
    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for role
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}