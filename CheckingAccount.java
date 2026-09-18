package bank;

public class CheckingAccount extends BankAccount {
    private static final double TRANSACTION_FEE = 10.0;

    public CheckingAccount(String accountNumber, String holderName, double openingBalance) {
        super(accountNumber, holderName, openingBalance);
    }

    @Override
    public String getType() {
        return "CHECKING";
    }

    @Override
    public void deposit(double amount) throws BankException {
        if (amount <= TRANSACTION_FEE) {
            throw new IllegalArgumentException("Deposit must be greater than the transaction fee.");
        }
        super.deposit(amount);
        changeBalance(-TRANSACTION_FEE);
    }

    @Override
    public void withdraw(double amount)
            throws BankException, BankException {
        super.withdraw(amount);
        if (getBalance() < TRANSACTION_FEE) {
            changeBalance(-getBalance());
            throw new BankException("Transaction fee could not be covered in checking account.");
        }
        changeBalance(-TRANSACTION_FEE);
    }
}
