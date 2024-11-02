package com.hexavarsity.ais.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

// User Entity
@Entity
@Table(name = "User")
public class User {

    /************************************ Attributes ************************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(unique = true, length = 12)
    private String aadhaar;

    @Column(unique = true, length = 10)
    private String pan;

    @Column(nullable = false)
    private Date dob;

    private String address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proposal> proposals;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;


    /************************************ Getters and Setters ************************************/

    // Getters and Setters for userId
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // Getters and Setters for username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // Getters and Setters for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Getters and Setters for email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Getters and Setters for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getters and Setters for role
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    // Getters and Setters for aadhaar
    public String getAadhaar() {
        return aadhaar;
    }
    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    // Getters and Setters for pan
    public String getPan() {
        return pan;
    }
    public void setPan(String pan) {
        this.pan = pan;
    }

    // Getters and Setters for dob
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }

    // Getters and Setters for address
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    // Getters and Setters for proposals
    public List<Proposal> getProposals() {
        return proposals;
    }
    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }

    // Getters and Setters for notifications
    public List<Notification> getNotifications() {
        return notifications;
    }
    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}

// Enum for Role
enum Role {
    CUSTOMER, OFFICER
}