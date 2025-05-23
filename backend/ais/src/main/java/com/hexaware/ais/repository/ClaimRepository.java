package com.hexaware.ais.repository;

import java.util.List;

import com.hexaware.ais.entity.Claim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
 * @Author: Kishlay Kumar
 * Class: Repository for Claim entity.
 * Description: This interface is used to interact with the Claim entity in the database.
 */
@Repository
public interface ClaimRepository extends JpaRepository<Claim, String> {

    // [SELECT * FROM claim WHERE proposal_id = ?]
    List<Claim> findByProposalProposalId(String proposalId);
}