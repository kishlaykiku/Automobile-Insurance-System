package com.hexavarsity.ais.entity;

import jakarta.persistence.*;
import java.util.Date;

// Notification Entity
@Entity
@Table(name = "Notification")
public class Notification {

    /************************************ Attributes ************************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Lob
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    
    /************************************ Getters and Setters ************************************/

    // Getters and Setters for notificationId
    public Integer getNotificationId() {
        return notificationId;
    }
    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    // Getters and Setters for user
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    // Getters and Setters for message
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    // Getters and Setters for date
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    // Getters and Setters for type
    public NotificationType getType() {
        return type;
    }
    public void setType(NotificationType type) {
        this.type = type;
    }
}

// Enum NotificationType
enum NotificationType {
    REMINDER, STATUS_UPDATE
}