package com.hexaware.ais.dto;

import java.time.LocalDate;

import com.hexaware.ais.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


/*
 * @Author: Kishlay Kumar
 * Class: UserDTO
 * Description: Data Transfer Object for the User entity
 */
public class UserDTO {

    /******************************************* Attributes *******************************************/

    private String userId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    private String address;

    @Past(message = "Date of Birth must be in the past")
    private LocalDate dob;

    @Size(min = 12, max = 12, message = "Aadhaar number must be 12 digits")
    private String aadharNo;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN number format")
    private String panNo;
    private LocalDate createdAt;

    /******************************************* Constructors *******************************************/

    // Default Constructor
    public UserDTO() {

        super();
    }

    // Constructor to map User entity to UserDTO
    public UserDTO(User user) {

        super();

        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.address = user.getAddress();
        this.dob = user.getDob();
        this.aadharNo = user.getAadharNo();
        this.panNo = user.getPanNo();
        this.createdAt = user.getCreatedAt();
    }

    /******************************************* Getters and Setters *******************************************/

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAadharNo() {
        return aadharNo;
    }
    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getPanNo() {
        return panNo;
    }
    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    /******************************************* Utility Methods *******************************************/

    // Convert DTO to Entity
    public User toEntity() {

        User user = new User();

        user.setUserId(this.userId);
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setAddress(this.address);
        user.setDob(this.dob);
        user.setAadharNo(this.aadharNo);
        user.setPanNo(this.panNo);

        return user;
    }
}