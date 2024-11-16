package com.hexaware.ais.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/*
 * @Author: Kishlay Kumar
 * Class: AuditLog Entity
 * Description: This class is used to represent the AuditLog entity
 * Map: It is mapped to the `audit_log` table in the database
 */
@Entity
@Table(name = "audit_log")
public class AuditLog {

    /******************************************* Attributes *******************************************/

    @Id
    @Column(name = "log_id", nullable = false, unique = true)
    private String logId;

    @NotBlank(message = "Entity type is required")
    @Column(name = "entity_type", nullable = false)
    private String entityType;

    @NotBlank(message = "Entity ID is required")
    @Column(name = "entity_id", nullable = false)
    private String entityId;

    @NotBlank(message = "Action is required")
    @Column(name = "action", nullable = false)
    private String action;

    @NotNull(message = "Timestamp is required")
    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @Column(name = "details")
    private String details;

    @PrePersist
    protected void initializeLog() {

        if (this.logId == null) {

            this.logId = UUID.randomUUID().toString();
        }
        this.timestamp = LocalDateTime.now();
    }

    /******************************************* Getters and Setters *******************************************/

    // Getter and Setter for logId
    public String getLogId() {
        return logId;
    }
    public void setLogId(String logId) {
        this.logId = logId;
    }

    // Getter and Setter for entityType
    public String getEntityType() {
        return entityType;
    }
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    // Getter and Setter for entityId
    public String getEntityId() {
        return entityId;
    }
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    // Getter and Setter for action
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    // Getter and Setter for timestamp
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Getter and Setter for details
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
}