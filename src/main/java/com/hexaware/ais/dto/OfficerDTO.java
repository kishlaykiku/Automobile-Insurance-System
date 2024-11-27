package com.hexaware.ais.dto;

import com.hexaware.ais.entity.Officer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/*
 * @Author: Kishlay Kumar
 * Class: OfficerDTO
 * Description: This class is used to transfer Officer data between layers
 */
public class OfficerDTO {

    /******************************************* Attributes *******************************************/

    private String officerId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Role is required")
    private String role;

    /******************************************* Constructors *******************************************/

    // Default constructor
    public OfficerDTO() {

        super();
    }

    // Constructor to map Officer entity to OfficerDTO
    public OfficerDTO(Officer officer) {

        super();

        this.officerId = officer.getOfficerId();
        this.name = officer.getName();
        this.email = officer.getEmail();
        this.role = officer.getRole();
    }

    /******************************************* Getters and Setters *******************************************/

    public String getOfficerId() {
        return officerId;
    }
    public void setOfficerId(String officerId) {
        this.officerId = officerId;
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

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    /******************************************* Methods *******************************************/

    // Method to map OfficerDTO back to Officer entity
    public Officer toEntity() {

        Officer officer = new Officer();

        officer.setOfficerId(this.officerId);
        officer.setName(this.name);
        officer.setEmail(this.email);
        officer.setRole(this.role);

        return officer;
    }
}