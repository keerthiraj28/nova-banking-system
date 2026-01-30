# Nova Banking System

A console-based banking application built with Java and MySQL, simulating real-world banking operations such as account management, transactions, and debit card services.

## Features
- Account creation and secure login
- Balance enquiry, deposit, and withdrawal
- Debit card services (apply, block, unblock)
- Profile management (name, email, mobile number, password)
- Nominee management
- Data persistence using MySQL

## Tech Stack
- Java
- MySQL
- JDBC

## Database Setup
Run the SQL script in `/database/schema.sql` to create required tables.

## Setup Note
Database configuration is loaded from a `db.properties` file and excluded from version control using `.gitignore` to protect sensitive credentials.

## Project Status
Core functionality is complete.  
Security improvements (such as password hashing) and additional refinements are planned.

## Note
Database credentials are **not committed** to this repository.
