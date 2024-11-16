package com.hexaware.ais.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/*
 * @Author: Kishlay Kumar 
 * Class: User Entity
 * Description: This class is used to represent the User entity
 * Map: It is mapped to the `user` table in the database
 */
@Entity
@Table(name = "user")
public class User {

    /******************************************* Attributes *******************************************/

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

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

    @Column(name = "address")
    private String address;

    @Past(message = "Date of Birth must be in the past")
    @Column(name = "dob")
    private LocalDate dob;

    @Size(min = 12, max = 12, message = "Aadhaar number must be 12 digits")
    @Column(name = "aadhar_no", unique = true)
    private String aadharNo;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN number format")
    @Column(name = "pan_no", unique = true)
    private String panNo;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {
    
        if (this.userId == null) {

            this.userId = UUID.randomUUID().toString();
        }
        this.createdAt = LocalDate.now();
    }

    /******************************************* Getters and Setters *******************************************/

    // Getter and Setter for userId
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
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

    // Getter and Setter for address
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter and Setter for dob
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    // Getter and Setter for aadharNo
    public String getAadharNo() {
        return aadharNo;
    }
    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    // Getter and Setter for panNo
    public String getPanNo() {
        return panNo;
    }
    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    // Getter for createdAt
    public LocalDate getCreatedAt() {
        return createdAt;
    }
}