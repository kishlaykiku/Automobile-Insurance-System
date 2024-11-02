-- Created Database `InsuranceSystem`
CREATE DATABASE InsuranceSystem;

USE InsuranceSystem;


/************************************* Table Structure *************************************/
-- User Table
CREATE TABLE User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    role ENUM('CUSTOMER', 'OFFICER') NOT NULL,
    aadhaar VARCHAR(12) UNIQUE,
    pan VARCHAR(10) UNIQUE,
    dob DATE NOT NULL,
    address VARCHAR(255)
);

-- Policy Table
CREATE TABLE Policy (
    policy_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    base_premium DECIMAL(10, 2) NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'
);

-- PolicyAddOn Table
CREATE TABLE PolicyAddOn (
    addon_id INT AUTO_INCREMENT PRIMARY KEY,
    policy_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    eligibility TEXT,
    FOREIGN KEY (policy_id) REFERENCES Policy(policy_id) ON DELETE CASCADE
);

-- Proposal Table
CREATE TABLE Proposal (
    proposal_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    policy_id INT NOT NULL,
    vehicle_type ENUM('CAR', 'MOTORCYCLE', 'CAMPERVAN', 'TRUCK') NOT NULL,
    status ENUM('SUBMITTED', 'QUOTE_GENERATED', 'ACTIVE', 'EXPIRED', 'REJECTED') DEFAULT 'SUBMITTED',
    submitted_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    quote DECIMAL(10, 2),
    policy_document_url VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
    FOREIGN KEY (policy_id) REFERENCES Policy(policy_id) ON DELETE CASCADE
);

-- Payment Table
CREATE TABLE Payment (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    proposal_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('PENDING', 'COMPLETED') DEFAULT 'PENDING',
    receipt_url VARCHAR(255),
    FOREIGN KEY (proposal_id) REFERENCES Proposal(proposal_id) ON DELETE CASCADE
);

-- Claim Table
CREATE TABLE Claim (
    claim_id INT AUTO_INCREMENT PRIMARY KEY,
    proposal_id INT NOT NULL,
    incident_date DATE NOT NULL,
    description TEXT,
    status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
    amount_claimed DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (proposal_id) REFERENCES Proposal(proposal_id) ON DELETE CASCADE
);

-- DocumentRequest Table
CREATE TABLE DocumentRequest (
    request_id INT AUTO_INCREMENT PRIMARY KEY,
    proposal_id INT NOT NULL,
    request_type VARCHAR(100) NOT NULL,
    status ENUM('PENDING', 'COMPLETED') DEFAULT 'PENDING',
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (proposal_id) REFERENCES Proposal(proposal_id) ON DELETE CASCADE
);

-- Notification Table
CREATE TABLE Notification (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    type ENUM('REMINDER', 'STATUS_UPDATE') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
);


/* Will use these for testing purpose */
DROP DATABASE InsuranceSystem;

DROP TABLE User;
DROP TABLE Policy;
DROP TABLE PolicyAddOn;
DROP TABLE Proposal;
DROP TABLE Payment;
DROP TABLE Claim;
DROP TABLE DocumentRequest;
DROP TABLE Notification;

SELECT * FROM User;
SELECT * FROM Policy;
SELECT * FROM PolicyAddOn;
SELECT * FROM Proposal;
SELECT * FROM Payment;
SELECT * FROM Claim;
SELECT * FROM DocumentRequest;
SELECT * FROM Notification;