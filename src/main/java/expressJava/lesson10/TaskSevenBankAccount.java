package expressJava.lesson10;

import java.util.concurrent.locks.ReentrantLock;

public class TaskSevenBankAccount {
    private int sum;
    private final ReentrantLock reentrantLock = new ReentrantLock();

    TaskSevenBankAccount(int sum) {
        this.sum = sum;
    }

    public void sendMoney(int moneyToSend, TaskSevenBankAccount account) {

        TaskSevenBankAccount first = this;
        TaskSevenBankAccount second = account;

        int h1 = System.identityHashCode(first);
        int h2 = System.identityHashCode(second);

        if (h1 > h2) {
            first = account;
            second = this;
        }

        first.reentrantLock.lock();
        try {
            second.reentrantLock.lock();
            try {
                this.sum -= moneyToSend;
                account.sum += moneyToSend;
            } finally {
                second.reentrantLock.unlock();
            }
        } finally {
            first.reentrantLock.unlock();
        }
    }

    public void printMoney() {
        reentrantLock.lock();
        try {
            System.out.println("Money: " + this.sum);
        } finally {
            reentrantLock.unlock();
        }
    }
}
