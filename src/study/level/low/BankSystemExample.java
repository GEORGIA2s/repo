package study.level.low;

import java.util.HashMap;
import java.util.Map;

enum AccountType {
    SAVINGS,
    CHECKING,
    LOAN
}

class BankAccount<T extends Enum<T>> {
    private String accountNumber;
    private double balance;
    private T type;

    public BankAccount(String accountNumber, double initialBalance, T type) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public T getType() {
        return type;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + " into account " + accountNumber);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn $" + amount + " from account " + accountNumber);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds");
        }
    }

    public void printAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Type: " + type);
        System.out.println("Balance: $" + balance);
    }
}

class Bank<T extends Enum<T>> {
    private Map<String, BankAccount<?>> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public void openAccount(String accountNumber, double initialBalance, T type) {
        BankAccount<?> account = new BankAccount<>(accountNumber, initialBalance, type);
        accounts.put(accountNumber, account);
        System.out.println("Account opened successfully!");
    }

    public void closeAccount(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            accounts.remove(accountNumber);
            System.out.println("Account " + accountNumber + " closed successfully!");
        } else {
            System.out.println("Account not found");
        }
    }

    public BankAccount<?> getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void printAllAccounts() {
        System.out.println("All Accounts:");

        for (Map.Entry<String, BankAccount<?>> entry : accounts.entrySet()) {
            String accountNumber = entry.getKey();
            BankAccount<?> account = entry.getValue();

            System.out.println("Account Number: " + accountNumber + " | Type: " + account.getType() + " | Balance: $" + account.getBalance());
        }
    }
}

public class BankSystemExample {
    public static void main(String[] args) {
        Bank<AccountType> myBank = new Bank<>();

        myBank.openAccount("123456", 1000, AccountType.SAVINGS);
        myBank.openAccount("789012", 500, AccountType.CHECKING);
        myBank.openAccount("345678", 2000, AccountType.LOAN);

        myBank.printAllAccounts();

        BankAccount<?> savingsAccount = myBank.getAccount("123456");
        savingsAccount.deposit(500);
        savingsAccount.withdraw(200);

        BankAccount<?> loanAccount = myBank.getAccount("345678");
        loanAccount.deposit(100);
        loanAccount.withdraw(2500);

        myBank.printAllAccounts();

        myBank.closeAccount("789012");
        myBank.printAllAccounts();
    }
}

