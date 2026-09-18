package bank;

public class ValidationTest {
    public static void main(String[] args) throws Exception {
        Bank bank = new Bank("data/test-bank-data.csv");
        new java.io.File("data/test-bank-data.csv").delete();
        bank = new Bank("data/test-bank-data.csv");
        bank.addAccount(new SavingsAccount("TEST1001", "Test User", 1000));
        bank.addAccount(new CheckingAccount("TEST1002", "Test User 2", 1000));

        try {
            bank.withdraw("TEST1001", 5000);
            System.out.println("FAIL: insufficient funds accepted");
        } catch (BankException e) {
            System.out.println("PASS: insufficient funds rejected");
        }

        try {
            bank.transfer("TEST1001", "TEST1001", 100);
            System.out.println("FAIL: self transfer accepted");
        } catch (BankException e) {
            System.out.println("PASS: self transfer rejected");
        }

        bank.getAccount("TEST1001").lock();
        try {
            bank.deposit("TEST1001", 100);
            System.out.println("FAIL: locked account accepted deposit");
        } catch (BankException e) {
            System.out.println("PASS: locked account rejected");
        }

        new java.io.File("data/test-bank-data.csv").delete();
        System.out.println("Validation tests completed.");
    }
}
