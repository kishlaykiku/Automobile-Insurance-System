package com.hexaware.ais.repository;

import java.util.List;

import com.hexaware.ais.entity.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
 * @Author: Kishlay Kumar
 * Class: Repository for Payment entity.
 * Description: This interface is used to interact with the Payment entity in the database.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    List<Payment> findByProposalProposalId(String proposalId);
}