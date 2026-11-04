package Day_82;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class Day82TryLockDemo {
    private static final ReentrantLock lock =
            new ReentrantLock();

    private static final CountDownLatch locked =
            new CountDownLatch(1);

    public static void main(String[] args)
            throws InterruptedException {

        Thread threadA = new Thread(() -> {

            lock.lock();

            try {
                System.out.println("线程A获得锁");

                locked.countDown();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            } finally {
                lock.unlock();
                System.out.println("线程A释放锁");
            }

        }, "线程A");

        threadA.start();

        // 确保线程 A 已经获得锁
        locked.await();

        // main 尝试立即获得锁
        boolean success = lock.tryLock();

        if (success) {

            try {
                System.out.println("main获得锁");

            } finally {
                lock.unlock();
            }

        } else {

            System.out.println("main无法获得锁，不再等待");
        }

        threadA.join();
    }
}
