package bank;

import java.util.Scanner;

public class BankApp {
    private static int nextAccountId = 1001;

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("demo")) {
            runDemo();
            return;
        }

        Bank bank = new Bank("data/bank-data.csv");
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> createAccount(sc, bank);
                    case "2" -> deposit(sc, bank);
                    case "3" -> withdraw(sc, bank);
                    case "4" -> transfer(sc, bank);
                    case "5" -> repayLoan(sc, bank);
                    case "6" -> applyMonthlyUpdate(bank);
                    case "7" -> viewAccount(sc, bank);
                    case "8" -> listAccounts(bank);
                    case "9" -> toggleLock(sc, bank);
                    case "0" -> running = false;
                    default -> System.out.println("Please choose a valid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n================================");
        System.out.println("           DIGITAL BANK");
        System.out.println("================================");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Repay Loan");
        System.out.println("6. Apply Monthly Update");
        System.out.println("7. View Account");
        System.out.println("8. List Accounts");
        System.out.println("9. Lock / Unlock Account");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void createAccount(Scanner sc, Bank bank) {
        System.out.print("Holder name: ");
        String name = sc.nextLine().trim();
        System.out.println("1. Savings  2. Checking  3. Loan");
        System.out.print("Account type: ");
        String type = sc.nextLine().trim();
        System.out.print("Opening amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        String prefix = switch (type) {
            case "1" -> "SAV";
            case "2" -> "CHK";
            case "3" -> "LON";
            default -> throw new IllegalArgumentException("Unknown account type.");
        };
        String number = prefix + nextAccountId++;
        BankAccount account = switch (type) {
            case "1" -> new SavingsAccount(number, name, amount);
            case "2" -> new CheckingAccount(number, name, amount);
            default -> new LoanAccount(number, name, amount);
        };
        bank.addAccount(account);
        System.out.println("Account created: " + number);
    }

    private static void deposit(Scanner sc, Bank bank) throws Exception {
        System.out.print("Account number: ");
        String number = sc.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());
        bank.deposit(number, amount);
        System.out.println("Operation completed.");
    }

    private static void withdraw(Scanner sc, Bank bank) throws Exception {
        System.out.print("Account number: ");
        String number = sc.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());
        bank.withdraw(number, amount);
        System.out.println("Operation completed.");
    }

    private static void transfer(Scanner sc, Bank bank) throws Exception {
        System.out.print("From account: ");
        String from = sc.nextLine();
        System.out.print("To account: ");
        String to = sc.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());
        bank.transfer(from, to, amount);
        System.out.println("Transfer successful.");
    }

    private static void repayLoan(Scanner sc, Bank bank) throws Exception {
        System.out.print("Loan account number: ");
        String number = sc.nextLine();
        System.out.print("Repayment amount: ");
        double amount = Double.parseDouble(sc.nextLine());
        bank.deposit(number, amount);
        System.out.println("Loan repayment recorded.");
    }

    private static void applyMonthlyUpdate(Bank bank) {
        bank.updateAllAccounts();
        System.out.println("Monthly update applied.");
    }

    private static void viewAccount(Scanner sc, Bank bank) {
        System.out.print("Account number: ");
        String number = sc.nextLine();
        BankAccount account = bank.getAccount(number);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        printAccount(account);
    }

    private static void listAccounts(Bank bank) {
        if (bank.getAccounts().isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        for (BankAccount account : bank.getAccounts()) {
            printAccount(account);
        }
    }

    private static void toggleLock(Scanner sc, Bank bank) {
        System.out.print("Account number: ");
        String number = sc.nextLine();
        BankAccount account = bank.getAccount(number);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        if (account.isLocked()) {
            account.unlock();
            System.out.println("Account unlocked.");
        } else {
            account.lock();
            System.out.println("Account locked.");
        }
        bank.save();
    }

    private static void printAccount(BankAccount account) {
        System.out.printf("%s | %s | %s | %.2f | %s%n",
                account.getAccountNumber(), account.getType(), account.getHolderName(),
                account.getBalance(), account.isLocked() ? "Locked" : "Active");
    }

    private static void runDemo() {
        java.io.File demoFile = new java.io.File("data/demo-bank.csv");
        demoFile.delete();
        Bank bank = new Bank("data/demo-bank.csv");
        try {
            bank.addAccount(new SavingsAccount("SAV9001", "Aman", 10000));
            bank.addAccount(new CheckingAccount("CHK9001", "Riya", 6000));
            bank.addAccount(new LoanAccount("LON9001", "Kabir", 20000));
        } catch (Exception ignored) {
            // Demo can be run more than once without crashing on duplicate accounts.
        }
        try {
            bank.transfer("SAV9001", "CHK9001", 1500);
            bank.deposit("LON9001", 2000);
            bank.updateAllAccounts();
        } catch (Exception e) {
            System.out.println("Demo error: " + e.getMessage());
        }
        System.out.println("\nDemo accounts:");
        listAccounts(bank);
        System.out.println("Data saved to: data/demo-bank.csv");
    }
}
