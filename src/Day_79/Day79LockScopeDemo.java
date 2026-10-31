package Day_79;

import java.util.concurrent.CountDownLatch;

public class Day79LockScopeDemo {
    static class Worker {

        public synchronized void doWork() {

            System.out.println(
                    Thread.currentThread().getName()
                            + " 进入临界区"
            );

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            System.out.println(
                    Thread.currentThread().getName()
                            + " 离开临界区"
            );
        }
    }

    private static void runScenario(
            String title,
            Runnable taskA,
            Runnable taskB
    ) throws InterruptedException {

        System.out.println("\n========== " + title + " ==========");

        CountDownLatch startGate = new CountDownLatch(1);

        Thread threadA = new Thread(() -> {
            try {
                startGate.await();
                taskA.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "线程A");

        Thread threadB = new Thread(() -> {
            try {
                startGate.await();
                taskB.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "线程B");

        threadA.start();
        threadB.start();

        // 同时放行两个线程
        startGate.countDown();

        threadA.join();
        threadB.join();
    }

    public static void main(String[] args)
            throws InterruptedException {

        // 实验一：两个线程使用同一个对象
        Worker sharedWorker = new Worker();

        runScenario(
                "同一个对象",
                sharedWorker::doWork,
                sharedWorker::doWork
        );

        // 实验二：两个线程使用不同对象
        Worker workerA = new Worker();
        Worker workerB = new Worker();

        runScenario(
                "不同对象",
                workerA::doWork,
                workerB::doWork
        );
    }
}
