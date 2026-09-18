package bank;

public class SavingsAccount extends BankAccount {
    private static final double MONTHLY_RATE = 0.005;

    public SavingsAccount(String accountNumber, String holderName, double openingBalance) {
        super(accountNumber, holderName, openingBalance);
    }

    @Override
    public String getType() {
        return "SAVINGS";
    }

    @Override
    public void monthlyUpdate() throws BankException {
        super.monthlyUpdate();
        changeBalance(getBalance() * MONTHLY_RATE);
    }
}
