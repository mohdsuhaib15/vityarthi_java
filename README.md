# Digital Bank

A small command-line Java banking system that supports savings, checking, and loan accounts.

The project was made to practice core Java concepts in a practical setting. Instead of treating every account the same way, each account type has its own rules. Savings accounts earn monthly interest, checking accounts apply a small transaction fee, and loan accounts accept repayments.

The project is intentionally kept compact. It focuses on the banking logic rather than adding a GUI, external services, or a large application structure.

## What the system can do

### Account management

* Create Savings, Checking, and Loan accounts
* Find an account using its account number
* List all accounts
* Lock or unlock an account

### Transactions

* Deposit money
* Withdraw money
* Transfer money between accounts
* Repay a loan
* Apply a simple monthly update

### Validation

The system checks common problems such as:

* Insufficient funds
* Missing accounts
* Transfers to the same account
* Operations on locked accounts
* Invalid or non positive amounts

A custom `BankException` is used for the main banking errors so failures are handled cleanly instead of letting the program crash.

## Java concepts used

The project mainly uses:

* Inheritance and polymorphism
* Encapsulation
* HashMap for account lookup
* Custom exception handling
* Exception handling
* File I/O
* Collections
* `switch` expressions
* Basic synchronized transfer handling

The account classes share the common `BankAccount` base class, while the child classes override behavior where the rules are different.

## Project structure

```text
src/bank/
├── BankApp.java
├── BankAccount.java
├── SavingsAccount.java
├── CheckingAccount.java
├── LoanAccount.java
├── Bank.java
├── BankException.java
└── ValidationTest.java

run.sh
test.sh
README.md
statement.md
data/
out/
```

### Class overview

`BankApp.java` handles the menu and command line input.

`BankAccount.java` contains the common account fields and operations.

`SavingsAccount.java` adds monthly interest.

`CheckingAccount.java` applies a transaction fee.

`LoanAccount.java` handles loan balance and repayment.

`Bank.java` stores accounts in a `HashMap` and handles deposits, withdrawals, transfers, persistence, and monthly updates.

`BankException.java` contains the custom exception used for banking errors.

`ValidationTest.java` checks a few important invalid operations.

## Running the project

The project is designed to run directly from a terminal.

You need Java JDK 17 or later.

### Normal run

```bash
./run.sh
```

If the script does not have execute permission, use:

```bash
bash run.sh
```

### Demo run

A small demo is included to quickly check the main operations:

```bash
./run.sh demo
```

or:

```bash
bash run.sh demo
```

### Run validation tests

```bash
./test.sh
```

or:

```bash
bash test.sh
```

## Data

Account records are stored locally in:

```text
data/bank-data.csv
```

The program creates the file when needed. A separate test data file is used during validation tests.

## A few rules used in the project

Savings accounts receive a simple monthly interest update of `0.5%`.

Checking accounts use a transaction fee of `₹10` for supported transactions.

Loan repayments reduce the outstanding loan balance and cannot make it negative.

A locked account cannot be used for normal deposit, withdrawal, or transfer operations.

These values are kept simple on purpose so the behavior is easy to understand and test.

## Keeping the project manageable

This is a learning project, not a production banking system. The main focus is the account hierarchy, different account behavior, safe transfers, exceptions, and basic persistence.

Features such as real authentication, a GUI, online payments, encryption, and a full banking database are outside the scope of this version.

## Author

Yash Rao Patankar  
Integrated M.Tech in Computational and Data Science  
VIT Bhopal University
