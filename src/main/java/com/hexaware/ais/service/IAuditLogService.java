package com.hexaware.ais.service;

import java.util.List;

import com.hexaware.ais.entity.AuditLog;


/*
 * @Author: Kishlay Kumar
 * Interface: IAuditLogService
 * Description: This interface defines the contract for audit log-related operations
 */
public interface IAuditLogService {

    /******************************************* Method Signatures *******************************************/

    // Create a new audit log entry
    AuditLog createAuditLog(AuditLog auditLog);

    // Get an audit log by ID
    AuditLog getAuditLogById(String logId);

    // Get all audit logs
    List<AuditLog> getAllAuditLogs();

    // Get audit logs by entity type
    List<AuditLog> getAuditLogsByEntityType(String entityType);
}