package com.hexaware.ais.repository;

import com.hexaware.ais.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * @Author: Kishlay Kumar
 * Class: Repository for Proposal entity.
 * Description: This interface is used to interact with the Proposal entity in the database.
 */
@Repository
public interface ProposalRepository extends JpaRepository<Proposal, String> {

    List<Proposal> findByUserUserId(String userId);
}