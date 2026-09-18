package bank;

public class LoanAccount extends BankAccount {
    public LoanAccount(String accountNumber, String holderName, double loanBalance) {
        super(accountNumber, holderName, loanBalance);
    }

    @Override
    public String getType() {
        return "LOAN";
    }

    @Override
    public void deposit(double amount) throws BankException {
        checkUnlocked();
        if (amount <= 0) {
            throw new IllegalArgumentException("Repayment must be greater than zero.");
        }
        double payment = Math.min(amount, getBalance());
        changeBalance(-payment);
    }

    @Override
    public void withdraw(double amount)
            throws BankException, BankException {
        throw new BankException("Money cannot be withdrawn from a loan account.");
    }

    @Override
    public void monthlyUpdate() throws BankException {
        checkUnlocked();
        if (getBalance() > 0) {
            changeBalance(getBalance() * 0.01);
        }
    }
}
