CREATE DATABASE IF NOT EXISTS `insurancesystem`;

USE `insurancesystem`;


-- User Table
CREATE TABLE user (
    user_id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    dob DATE,
    aadhar_no VARCHAR(255) UNIQUE,
    pan_no VARCHAR(255) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Vehicle Table
CREATE TABLE vehicle (
    vehicle_id VARCHAR(255) PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    registration_no VARCHAR(255) UNIQUE NOT NULL,
    year INT NOT NULL,
    user_id VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- Policy Table
CREATE TABLE policy (
    policy_id VARCHAR(255) PRIMARY KEY,
    policy_no VARCHAR(255) UNIQUE NOT NULL,
    type VARCHAR(255) NOT NULL,
    base_premium DECIMAL(10, 2) NOT NULL,
    features TEXT,
    add_ons TEXT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    renewal_date DATE,
    status VARCHAR(255) NOT NULL,
    reminder_sent BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Officer Table
CREATE TABLE officer (
    officer_id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

-- Proposal Table
CREATE TABLE proposal (
    proposal_id VARCHAR(255) PRIMARY KEY,
    submission_date DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    additional_docs TEXT,
    user_id VARCHAR(255),
    vehicle_id VARCHAR(255),
    policy_id VARCHAR(255),
    officer_id VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id),
    FOREIGN KEY (policy_id) REFERENCES policy(policy_id),
    FOREIGN KEY (officer_id) REFERENCES officer(officer_id)
);

-- Payment Table
CREATE TABLE payment (
    payment_id VARCHAR(255) PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date DATE NOT NULL,
    payment_method VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    proposal_id VARCHAR(255),
    FOREIGN KEY (proposal_id) REFERENCES proposal(proposal_id)
);

-- Claim Table
CREATE TABLE claim (
    claim_id VARCHAR(255) PRIMARY KEY,
    proposal_id VARCHAR(255),
    claim_date DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    remarks TEXT,
    FOREIGN KEY (proposal_id) REFERENCES proposal(proposal_id)
);

-- Audit Log Table
CREATE TABLE audit_log (
    log_id VARCHAR(255) PRIMARY KEY,
    entity_type VARCHAR(255) NOT NULL,
    entity_id VARCHAR(255) NOT NULL,
    action VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    details TEXT
);

-- Total Modules: 8
-- [User, Vehicle, Policy, Officer, Proposal, Payment, Claim, Audit Log]


-- For Testing
DROP DATABASE `insurancesystem`;

SELECT * FROM user;
SELECT * FROM vehicle;
SELECT * FROM policy;
SELECT * FROM officer;
SELECT * FROM proposal;
SELECT * FROM payment;
SELECT * FROM claim;
SELECT * FROM audit_log;