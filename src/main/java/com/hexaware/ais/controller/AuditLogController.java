package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.entity.AuditLog;
import com.hexaware.ais.service.IAuditLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/*
 * @Author: Kishlay Kumar
 * Class: AuditLogController
 * Description: This class handles HTTP requests related to audit log operations
 */
@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    /******************************************* Dependencies *******************************************/

    @Autowired
    private IAuditLogService auditLogService;

    /******************************************* Endpoints *******************************************/

    // Create a new audit log entry
    @PostMapping
    public ResponseEntity<AuditLog> createAuditLog(@RequestBody AuditLog auditLog) {

        AuditLog createdAuditLog = auditLogService.createAuditLog(auditLog);

        return ResponseEntity.ok(createdAuditLog);
    }

    // Get an audit log by ID
    @GetMapping("/{logId}")
    public ResponseEntity<AuditLog> getAuditLogById(@PathVariable String logId) {

        AuditLog auditLog = auditLogService.getAuditLogById(logId);

        return ResponseEntity.ok(auditLog);
    }

    // Get all audit logs
    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllAuditLogs() {

        List<AuditLog> auditLogs = auditLogService.getAllAuditLogs();

        return ResponseEntity.ok(auditLogs);
    }

    // Get audit logs by entity type
    @GetMapping("/entity/{entityType}")
    public ResponseEntity<List<AuditLog>> getAuditLogsByEntityType(@PathVariable String entityType) {

        List<AuditLog> auditLogs = auditLogService.getAuditLogsByEntityType(entityType);

        return ResponseEntity.ok(auditLogs);
    }
}