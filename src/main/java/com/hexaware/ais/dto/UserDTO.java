package com.hexaware.ais.dto;

import java.time.LocalDate;

import com.hexaware.ais.entity.User;

/*
 * @Author: Kishlay Kumar
 * Class: UserDTO
 * Description: Data Transfer Object for the User entity
 */
public class UserDTO {

    /******************************************* Attributes *******************************************/

    private String userId;
    private String name;
    private String email;
    private String password;
    private String address;
    private LocalDate dob;
    private String aadharNo;
    private String panNo;
    private LocalDate createdAt;

    /******************************************* Constructors *******************************************/

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