# 🏦 Digital Bank

A simple command-line banking system built with **Java**. This project was created as a college project to practice Object-Oriented Programming and understand how different types of bank accounts can have different rules while sharing common functionality.

The system supports **Savings, Checking, and Loan accounts** along with basic banking operations, account restrictions, validation, and local CSV data storage.

## 📌 Problem Statement

Basic ATM programs usually handle only simple operations like deposits, withdrawals, and balance checking. This project goes a step further by modelling different account types with their own rules.

For example:

- Savings accounts earn monthly interest.
- Checking accounts have a transaction fee.
- Loan accounts accept repayments but do not allow withdrawals.
- Accounts can be locked or unlocked.
- Money can be transferred between accounts.

The project is kept small and easy to understand so that the focus remains on Java and OOP concepts.

## ✨ Features

- Create Savings, Checking, and Loan accounts
- Automatically generate account numbers
- Deposit money
- Withdraw money
- Transfer money between accounts
- Repay loans
- Apply monthly account updates
- Lock and unlock accounts
- View one account or list all accounts
- Save and load account data using CSV files
- Handle invalid operations using exceptions
- Run basic validation tests

## 💳 Account Types

| Account | Main Rule |
|---|---|
| **Savings** | Earns 0.5% monthly interest |
| **Checking** | Charges a ₹10 transaction fee |
| **Loan** | Accepts repayments and adds 1% monthly interest |

Loan accounts do not allow withdrawals.

## 🧠 OOP Concepts Used

The project mainly focuses on:

- **Abstraction** – `BankAccount` is an abstract base class.
- **Inheritance** – Savings, Checking, and Loan accounts extend `BankAccount`.
- **Polymorphism** – Different account types implement their own behaviour.
- **Encapsulation** – Account data such as balance and lock status is managed inside the account class.
- **Exception Handling** – `BankException` is used for banking-related errors.

```text
                 BankAccount
                     │
          ┌──────────┼──────────┐
          │          │          │
          ▼          ▼          ▼
      Savings     Checking     Loan
      Account     Account     Account
📂 Project Structure
digital-bank/
│
├── src/
│   └── bank/
│       ├── Bank.java
│       ├── BankAccount.java
│       ├── BankApp.java
│       ├── BankException.java
│       ├── CheckingAccount.java
│       ├── LoanAccount.java
│       ├── SavingsAccount.java
│       └── ValidationTest.java
│
├── data/
│   ├── bank-data.csv
│   └── demo-bank.csv
│
├── out/
│   ├── classes/
│   └── test-classes/
│
├── run.sh
├── test.sh
├── statement.md
└── README.md
📄 Main Files
Bank.java – Manages accounts, transactions, transfers, monthly updates, and CSV data.
BankAccount.java – Abstract parent class containing common account functionality.
SavingsAccount.java – Handles savings account behaviour and monthly interest.
CheckingAccount.java – Handles checking accounts and transaction fees.
LoanAccount.java – Handles loan repayments, restrictions, and loan interest.
BankException.java – Custom exception for banking errors.
BankApp.java – Main command-line application and user menu.
ValidationTest.java – Tests important invalid operations.
run.sh – Compiles and runs the application.
test.sh – Compiles and runs the validation tests.
statement.md – Contains the problem statement, scope, target users, and project requirements.
💰 Banking Operations

The application provides a simple menu:

================================
           DIGITAL BANK
================================
1. Create Account
2. Deposit Money
3. Withdraw Money
4. Transfer Money
5. Repay Loan
6. Apply Monthly Update
7. View Account
8. List Accounts
9. Lock / Unlock Account
0. Exit
🔐 Validation & Error Handling

The system checks common problems such as:

Insufficient funds
Missing accounts
Invalid transaction amounts
Transfers to the same account
Operations on locked accounts
Withdrawals from loan accounts

A custom BankException is used to handle banking-related errors cleanly.

💾 Data Storage

Account information is stored locally using CSV files.

data/bank-data.csv
data/demo-bank.csv

The stored information includes:

accountNumber,type,holderName,balance,locked

This allows account data to be saved and loaded when the application starts.

🧪 Testing

The project includes basic validation tests for:

Insufficient funds
Self-transfer
Locked account operations

Run the tests using:

./test.sh
🎯 Project Scope

This project is mainly intended for students learning:

Java
Object-Oriented Programming
Inheritance and Polymorphism
Exception Handling
Collections
File Handling
CSV Storage
Basic Testing
Shell Scripting

It is an educational project and is not intended to be a real banking application. It does not include real authentication, online payments, encryption, or a production database.

📚 What I Learned

Through this project, I got practical experience with designing Java classes, connecting them through inheritance, handling different behaviours using polymorphism, working with collections and files, creating custom exceptions, and writing simple shell scripts and validation tests.

🔮 Future Improvements

Some possible improvements are:

Add a GUI
Add user authentication
Add transaction history
Add a database
Add JUnit tests
Improve input validation
Use BigDecimal for financial calculations
Add more account types
Add transaction receipts
