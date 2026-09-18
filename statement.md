# Problem Statement

Basic ATM programs usually focus on a few operations such as deposit, withdrawal, and balance checking. They do not show how different account types can follow different rules or how a banking system can safely handle transfers and account restrictions.

This project builds a small command-line digital bank that models savings, checking, and loan accounts. It applies common banking rules through Java classes and custom exceptions while keeping the implementation small enough to run and explain easily.

## Scope

The project covers account creation, deposits, withdrawals, transfers, loan repayment, monthly account updates, account locking, local data storage, and basic validation tests.

It is not intended to be a real banking application. It does not handle real customer authentication, online payments, encryption, or a production database.

## Target Users

The project is mainly intended for students learning Java and for a simple demonstration of object-oriented programming, collections, exception handling, and file handling.

## High Level Features

1. Create savings, checking, and loan accounts.
2. Deposit and withdraw money according to the account type.
3. Transfer money between valid accounts.
4. Handle invalid operations with a custom exception.
5. Lock and unlock accounts.
6. Apply a simple monthly interest update.
7. Save account data locally in CSV format.
8. Run basic validation tests.
