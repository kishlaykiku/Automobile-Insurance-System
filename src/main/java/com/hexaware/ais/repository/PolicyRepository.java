package com.hexaware.ais.repository;

import com.hexaware.ais.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @Author: Kishlay Kumar
 * Class: Repository for Policy entity.
 * Description: This interface is used to interact with the Policy entity in the database.
 */
@Repository
public interface PolicyRepository extends JpaRepository<Policy, String> {

}