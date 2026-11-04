package Day_82;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class Day82AccountDemo {
    static class Account {

        private int balance;

        private final ReentrantLock lock = new ReentrantLock();

        public Account(int balance) {
            this.balance = balance;
        }

        public boolean withdraw(int amount) {

            if (amount <= 0) {
                throw new IllegalArgumentException(
                        "取款金额必须大于0"
                );
            }

            lock.lock();

            try {

                if (balance < amount) {
                    return false;
                }

                balance -= amount;

                return true;

            } finally {
                lock.unlock();
            }
        }

        public int getBalance() {

            lock.lock();

            try {
                return balance;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        Account account = new Account(100);

        CountDownLatch startGate = new CountDownLatch(1);

        Runnable task = () -> {

            try {
                startGate.await();

                boolean success = account.withdraw(80);

                System.out.println(
                        Thread.currentThread().getName()
                                + " 取款结果：" + success
                );

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread threadA = new Thread(task, "线程A");
        Thread threadB = new Thread(task, "线程B");

        threadA.start();
        threadB.start();

        startGate.countDown();

        threadA.join();
        threadB.join();

        System.out.println(
                "最终余额：" + account.getBalance()
        );
    }
}
