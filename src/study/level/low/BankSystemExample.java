package study.level.low;

import java.util.HashMap;
import java.util.Map;

enum AccountType {
    SAVINGS, // 저축 계좌
    CHECKING,//체크 계좌
    LOAN // 대출 계좌
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
            System.out.println("입금내역 ₩" + amount + " 계좌로=> " + accountNumber);
        } else {
            System.out.println("입금 금액이 잘못되었습니다.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("출금내역 ₩" + amount + " 계좌에서=> " + accountNumber);
        } else {
            System.out.println("잘못된 출금 금액 또는 부족한 잔액입니다");
        }
    }

    public void printAccountInfo() {
        System.out.println("계좌 번호: " + accountNumber);
        System.out.println("계좌 타입: " + type);
        System.out.println("잔액: ₩" + balance);
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
        System.out.println("계좌 개설 성공!");
    }

    public void closeAccount(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            accounts.remove(accountNumber);
            System.out.println("계좌 " + accountNumber + " 폐쇄 성공!");
        } else {
            System.out.println("계좌를 찾을 수 없습니다.");
        }
    }

    public BankAccount<?> getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void printAllAccounts() {
        System.out.println("모든 계좌 목록:");

        for (Map.Entry<String, BankAccount<?>> entry : accounts.entrySet()) {
            String accountNumber = entry.getKey();
            BankAccount<?> account = entry.getValue();

            System.out.println("계좌 번호: " + accountNumber + " | 타입: " + account.getType() + " | 잔액: ₩" + (int)account.getBalance());
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
