create database bankdb;

use bankdb;

CREATE TABLE accounts (
    account_number INT AUTO_INCREMENT PRIMARY KEY,

    -- Personal Details
    name VARCHAR(100) NOT NULL,
    dob DATE NOT NULL,
    age INT NOT NULL,
    minor BOOLEAN,

    mobile_number VARCHAR(10),
    email VARCHAR(100),
    address TEXT NOT NULL,

    aadhaar_number VARCHAR(12) NOT NULL UNIQUE,
    pan_number VARCHAR(10),

    -- Guardian Details (for minors)
    guardian_name VARCHAR(100),
    guardian_mobile VARCHAR(10),
    guardian_email VARCHAR(100),
    guardian_pan VARCHAR(10),

    -- Account Details
    balance DECIMAL(12,2) NOT NULL,
    account_type VARCHAR(20) NOT NULL,

    -- Debit Card Details
    debit_card_number VARCHAR(16),
    debit_card_status ENUM('ACTIVE', 'BLOCKED', 'NONE') DEFAULT 'NONE',
    card_type VARCHAR(20),
    card_feature VARCHAR(20),

    -- Nominee Details
    nominee_name VARCHAR(100),
    nominee_age INT,
    nominee_relationship VARCHAR(50),
    nominee_mobile VARCHAR(10),
    nominee_pan VARCHAR(10),

    -- Login Details
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,

    -- Metadata
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
)AUTO_INCREMENT = 1274100;

select * from accounts;