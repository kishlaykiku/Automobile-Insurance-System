package com.hexaware.ais.repository;

import java.util.List;

import com.hexaware.ais.entity.AuditLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
 * @Author: Kishlay Kumar
 * Class: Repository for AuditLog entity.
 * Description: This interface is used to interact with the AuditLog entity in the database.
 */
@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, String> {

    List<AuditLog> findAllByEntityType(String entityType);
}