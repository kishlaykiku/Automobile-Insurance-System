package com.hexaware.ais.repository;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.ais.entity.Policy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/*
 * @Author: Kishlay Kumar
 * Class: Repository for Policy entity.
 * Description: This interface is used to interact with the Policy entity in the database.
 */
@Repository
public interface PolicyRepository extends JpaRepository<Policy, String> {

    // Count policies by status
    long countByStatus(String status);
    // [SELECT * FROM policy WHERE status = ?]

    // Find policies for a user
    @Query("SELECT DISTINCT p FROM Policy p JOIN Proposal pr ON p.policyId = pr.policy.policyId WHERE pr.user.userId = :userId")
    List<Policy> findPoliciesByUserId(@Param("userId") String userId);
    // [SELECT * FROM policy JOIN proposal ON policy.policy_id = proposal.policy_id WHERE proposal.user_id = ?]

    // Find policies expiring soon without reminders sent
    List<Policy> findByEndDateBetweenAndReminderSentFalse(LocalDate startDate, LocalDate endDate);
    // [SELECT * FROM policy WHERE end_date BETWEEN ? AND ? AND reminder_sent = false]
}