INSERT INTO officer (officer_id, name, email, password, role)
VALUES
('b43a7c7b-3a7d-4f8f-8b7e-3427c51e4d16', 'Mr Hola', 'admin@example.com', '$2y$10$UcptDaMen9XRPupCiiDvOes5uTRFZ0WJluh/NH2uYPTf2W7./3/2O', 'Admin');
 
INSERT INTO user (user_id, aadhar_no, address, created_at, dob, email, name, pan_no, password)
VALUES
('1e852684-2a02-4a1b-bde2-49d8c8511e1e', '223556389123', '123 Siruseri, Chennai', '2025-03-28', '2002-12-06', 'anup@example.com', 'Anup Pal', 'KWIPS2952L', '$2a$10$GSmyGOewBNOt6HpxffX/PuYLIiBsZA7P4gzbg0vIwlBJIr1q3h4SK'),
('6b3e171d-8dcf-46ef-8e80-9d6535898604', '123456789124', '123, Kiklu Street, Ohio', '2024-12-13', '2015-07-15', 'ishika.sangraj@example.com', 'Ishika Sangraj', 'ABCDE1234Z', '$2a$10$zhRnVt44C.jvfAe1ZBc.M.iQruqadqgNtyXLykw6UeqfgbOMOinFm'),
('6e06f77a-1ea5-4bd2-9e8b-a77a000b060d', '123456789012', '123 Elm Street, Springfield', '2024-11-23', '1990-06-15', 'john.doe@example.com', 'John Doe', 'ABCDE1234F', '$2y$10$PGc3JMrfU00Ml0SKuL17.evkxfyx.BoU7oNeApUOGk1fyyUAtOOxC'),
('9a85a00c-47f9-4972-a9f3-62b143a1f6c0', '113459783013', 'E129, Patel Nagar, Raisen Road', '2024-12-09', '1990-07-23', 'rohit.raj@example.com', 'Rohit Raj', 'LKRAE6481K', '$2a$10$Q3cMqPP1M6/BYeHN5AFOIe3cluGbL1djpeShG7IzYzeRXrzQUAH4C'),
('e37599f7-b6aa-4d82-b219-372507b8f2d3', '113457789012', '198 Chikotli Street, Hamburger', '2024-11-30', '2000-11-30', 'jane.alpha@example.com', 'Jane Alpha', 'LKRAE5485K', '$2y$10$dKeb1dPXk8RVgBF29OuBNeI5CnwBXOTEGaxVhBablS.3Fhp5d9F/O'),
('3331040f-3701-4cfe-a28c-d9a8d8dc7f8b', '223457789012', '201 Bankrupt Street, Detrtoit', '2024-11-30', '2001-11-30', 'flex.beta@example.com', 'Flex Beta', 'JKXAE5485K', '$2a$10$bLQMEd8Q19MQQp/S/JJuE.4HU3kyq19QnO0gjVU3w4OqWhJMVemIe')
;
 
INSERT INTO vehicle(vehicle_id, model, registration_no, type, year, user_id)
VALUES
('09cb74c7-07b0-492f-ad96-c537d10723fa', 'Maruti Swift', 'BR01XY4467', 'Car', 2015, '3331040f-3701-4cfe-a28c-d9a8d8dc7f8b'),
('2570897f-1f52-4dd3-a980-a843887b0d27', 'Toyota Corolla', 'XYZ1234', 'Car', 2018, '6e06f77a-1ea5-4bd2-9e8b-a77a000b060d'),
('7e83cbb8-87e9-41ad-9898-80f9580006e6', 'Maruti Celerio', 'BR01CD4567', 'Car', 2017, '3331040f-3701-4cfe-a28c-d9a8d8dc7f8b'),
('a94a63e4-b109-4da0-9530-82bff2752fb8', 'Maruti Celerio', 'TN01ZY4467', 'Car', 2025, '3331040f-3701-4cfe-a28c-d9a8d8dc7f8b');
 
INSERT INTO policy(policy_id, policy_name, add_ons, base_premium, created_at, end_date, features, policy_no, reminder_sent, renewal_date, start_date, status, type)
VALUES
('046e57de-e4c0-46f8-977d-231169bbffdd', 'AutoSecure Comprehensive Plan','Roadside Assistance, Zero Depreciation', 5000, '2024-11-23', '2038-12-01', 'Accidental Coverage, Theft Coverage', 'POL123456', false, '2025-11-15', '2024-12-01', 'Active', 'Comprehensive'),
('7047d18f-15b2-4a37-aa53-144d4f2741b2', 'DriveSafe Complete Coverage', 'Roadside Assistance, Engine Protection', 5000, '2024-11-30', '2045-12-01', 'Covers accidents, theft, and natural disasters', 'POL12345678', false, '2025-11-30', '2024-12-01', 'Active', 'Comprehensive'),
('8bd4e2ba-b7d8-4560-a190-a6c90fadae77', 'FlexiProtect Third-Party Plan', 'Engine Protection, Consumables Cover, Zero Depreciation Cover', 4000, '2024-12-17', '2040-01-17', 'Cancel anytime, Full refund', 'POL9991', false, '2025-06-17', '2024-12-17', 'Active', 'Third-Party Liability Insurance'),
('a8048ca6-0593-423a-b955-5e7e3eeb8bfe', 'InjuryShield Personal Cover', 'Engine Protection, Daily Allowance Cover, Repair of Accessories', 1200, '2024-12-16', '2040-01-16', 'We will not steal your money, Full refund on cancellation', 'POL12345654', false, '2025-06-16', '2024-12-16', 'Active', 'Personal Injury Protection'),
('f949fc52-8621-40ba-a7e5-4243f108515a', 'CrashCare Collision Advantage,' 'Engine Protection, Daily Allowance Cover, Consumables Cover, Roadside Assistance Cover', 8000, '2024-12-17', '2050-01-17', 'Cancel anytime, Full refund', 'POL9981', false, '2025-06-17', '2024-12-17', 'Active', 'Collision Insurance')
;
 
INSERT INTO proposal (proposal_id, additional_docs, remarks, status, submission_date, officer_id, policy_id, user_id, vehicle_id)
VALUES
('753aef19-2a85-444c-bccf-35e8cefdb66e', 'Vehicle Registration, Pollution Verification', 'Please approve', 'Proposal Submitted', '2025-03-28', 'b43a7c7b-3a7d-4f8f-8b7e-3427c51e4d16', '046e57de-e4c0-46f8-977d-231169bbffdd', '3331040f-3701-4cfe-a28c-d9a8d8dc7f8b', 'a94a63e4-b109-4da0-9530-82bff2752fb8'),
('ce6bcc77-05bf-47bb-a37d-69433d76114a', 'Aadhaar, Vehicle Registration, Pollution Verification', 'Proposal submitted for review', 'Proposal Submitted', '2024-12-17', 'b43a7c7b-3a7d-4f8f-8b7e-3427c51e4d16', '046e57de-e4c0-46f8-977d-231169bbffdd', '3331040f-3701-4cfe-a28c-d9a8d8dc7f8b', '09cb74c7-07b0-492f-ad96-c537d10723fa'),
('dec86902-4b36-42fe-abff-c7d11f871dd1', 'Vehicle Registration, Pollution Verification', 'Please Verify', 'Proposal Submitted', '2024-12-15', 'b43a7c7b-3a7d-4f8f-8b7e-3427c51e4d16', '046e57de-e4c0-46f8-977d-231169bbffdd', '3331040f-3701-4cfe-a28c-d9a8d8dc7f8b', '7e83cbb8-87e9-41ad-9898-80f9580006e6'),
('e1faae58-cc38-43db-8ab0-d207528497db', 'Driving License, Registration Certificate', 'Document verification failed', 'Rejected', '2024-11-23', 'b43a7c7b-3a7d-4f8f-8b7e-3427c51e4d16', '046e57de-e4c0-46f8-977d-231169bbffdd', '6e06f77a-1ea5-4bd2-9e8b-a77a000b060d', '2570897f-1f52-4dd3-a980-a843887b0d27')
;
 
INSERT INTO claim (claim_id, amount, claim_date, remarks, status, proposal_id)
VALUES
('d08d9bb7-22d2-40f8-803d-73f21fda0172', 15000, '2024-11-20', 'Initial claim for accidental damage.', 'Pending', 'e1faae58-cc38-43db-8ab0-d207528497db')
;
 
INSERT INTO payment (payment_id, amount, payment_date, payment_method, status, proposal_id)
VALUES
('251833c5-55f6-41a2-bab2-d574af777e42', 7500, '2024-11-24', 'Debit Card', 'Completed', 'e1faae58-cc38-43db-8ab0-d207528497db')
;