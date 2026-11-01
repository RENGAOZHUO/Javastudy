package day_80;

import java.util.concurrent.CountDownLatch;

public class Day80AccountDemo {
    static class Account {

        private int balance;

        private final Object lock = new Object();

        public Account(int balance) {
            this.balance = balance;
        }

        public boolean withdraw(int amount) {

            if (amount <= 0) {
                throw new IllegalArgumentException(
                        "取款金额必须大于0"
                );
            }

            synchronized (lock) {

                // 先检查余额
                if (balance < amount) {
                    return false;
                }

                // 再修改余额
                balance -= amount;

                return true;
            }
        }

        public int getBalance() {

            synchronized (lock) {
                return balance;
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

        // 同时放行两个线程
        startGate.countDown();

        threadA.join();
        threadB.join();

        System.out.println(
                "最终余额：" + account.getBalance()
        );
    }
}
