package lesson2;

public class BankAccount {
    String owner;
    int balance;

    BankAccount(String owner, int balance) {
        this.owner = owner;
        this.balance = balance;
    }

    String getOwner() {
        return owner;
    }

    int getBalance() {
        return balance;
    }

    void setOwner(String owner) {
        this.owner = owner;
    }

    void deposit(int amount) {
        balance = balance + amount;
    }

    void withdraw(int amount) {
        balance = balance - amount;
    }

    void printBalance() {
        System.out.println(balance);
    }
}
