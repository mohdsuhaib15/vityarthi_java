package bank;

import java.io.*;
import java.util.*;

public class Bank {
    private final Map<String, BankAccount> accounts = new HashMap<>();
    private final String dataFile;

    public Bank(String dataFile) {
        this.dataFile = dataFile;
        load();
    }

    public void addAccount(BankAccount account) {
        if (accounts.containsKey(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account number already exists.");
        }
        if (account.getBalance() < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }
        accounts.put(account.getAccountNumber(), account);
        save();
    }

    public BankAccount getAccount(String number) {
        return accounts.get(number);
    }

    public Collection<BankAccount> getAccounts() {
        return accounts.values();
    }

    public void deposit(String number, double amount) throws BankException {
        BankAccount account = requireAccount(number);
        account.deposit(amount);
        save();
    }

    public void withdraw(String number, double amount)
            throws BankException, BankException {
        BankAccount account = requireAccount(number);
        account.withdraw(amount);
        save();
    }

    public synchronized void transfer(String from, String to, double amount)
            throws BankException, BankException, BankException {
        if (from.equals(to)) {
            throw new BankException("Source and destination accounts must be different.");
        }
        if (amount <= 0) {
            throw new BankException("Transfer amount must be greater than zero.");
        }
        BankAccount source = requireAccount(from);
        BankAccount target = requireAccount(to);
        source.withdraw(amount);
        target.deposit(amount);
        save();
    }

    public void updateAllAccounts() {
        for (BankAccount account : accounts.values()) {
            try {
                account.monthlyUpdate();
            } catch (Exception ignored) {
                // Locked accounts are simply skipped during monthly update.
            }
        }
        save();
    }

    public void save() {
        File file = new File(dataFile);
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("accountNumber,type,holderName,balance,locked");
            for (BankAccount account : accounts.values()) {
                writer.println(account.toCsv());
            }
        } catch (IOException e) {
            System.out.println("Could not save bank data: " + e.getMessage());
        }
    }

    private void load() {
        File file = new File(dataFile);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length != 5) {
                    continue;
                }
                String number = parts[0];
                String type = parts[1];
                String name = parts[2];
                double balance = Double.parseDouble(parts[3]);
                boolean locked = Boolean.parseBoolean(parts[4]);
                BankAccount account = switch (type) {
                    case "SAVINGS" -> new SavingsAccount(number, name, balance);
                    case "CHECKING" -> new CheckingAccount(number, name, balance);
                    case "LOAN" -> new LoanAccount(number, name, balance);
                    default -> null;
                };
                if (account != null) {
                    if (locked) account.lock();
                    accounts.put(number, account);
                }
            }
        } catch (Exception e) {
            System.out.println("Could not load saved bank data. Starting with available records only.");
        }
    }

    private BankAccount requireAccount(String number) {
        BankAccount account = accounts.get(number);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + number);
        }
        return account;
    }
}
