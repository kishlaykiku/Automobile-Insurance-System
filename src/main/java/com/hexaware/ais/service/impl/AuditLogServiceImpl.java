package com.hexaware.ais.service.impl;

import java.util.List;
import java.util.Optional;

import com.hexaware.ais.entity.AuditLog;
import com.hexaware.ais.repository.AuditLogRepository;
import com.hexaware.ais.service.IAuditLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * @Author: Kishlay Kumar
 * Class: AuditLogServiceImpl
 * Description: This class implements the IAuditLogService interface for audit log-related operations
 */
@Service
public class AuditLogServiceImpl implements IAuditLogService {

    /******************************************* Dependencies *******************************************/

    @Autowired
    private AuditLogRepository auditLogRepository;

    /******************************************* Methods *******************************************/

    @Override
    public AuditLog createAuditLog(AuditLog auditLog) {

        return auditLogRepository.save(auditLog);
    }

    @Override
    public AuditLog getAuditLogById(String logId) {

        Optional<AuditLog> auditLog = auditLogRepository.findById(logId);

        return auditLog.orElseThrow(() -> new RuntimeException("Audit Log not found with ID: " + logId));
    }

    @Override
    public List<AuditLog> getAllAuditLogs() {

        return auditLogRepository.findAll();
    }

    @Override
    public List<AuditLog> getAuditLogsByEntityType(String entityType) {

        return auditLogRepository.findAllByEntityType(entityType);
    }
}