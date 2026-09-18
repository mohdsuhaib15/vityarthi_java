package bank;

public abstract class BankAccount {
    private final String accountNumber;
    private final String holderName;
    private double balance;
    private boolean locked;

    protected BankAccount(String accountNumber, String holderName, double openingBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = openingBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isLocked() {
        return locked;
    }

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }

    protected void changeBalance(double amount) {
        balance += amount;
    }

    public void deposit(double amount) throws BankException {
        checkUnlocked();
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        changeBalance(amount);
    }

    public void withdraw(double amount)
            throws BankException, BankException {
        checkUnlocked();
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new BankException("Not enough balance in " + accountNumber + ".");
        }
        changeBalance(-amount);
    }

    protected void checkUnlocked() throws BankException {
        if (locked) {
            throw new BankException("Account " + accountNumber + " is locked.");
        }
    }

    public abstract String getType();

    public void monthlyUpdate() throws BankException {
        checkUnlocked();
    }

    public String toCsv() {
        return String.format("%s,%s,%s,%.2f,%s", accountNumber, getType(),
                holderName.replace(",", " "), balance, locked);
    }
}
