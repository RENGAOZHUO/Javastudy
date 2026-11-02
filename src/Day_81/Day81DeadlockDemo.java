package Day_81;

import java.util.concurrent.CountDownLatch;

public class Day81DeadlockDemo {
    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    // 确保两个线程都拿到第一把锁
    private static final CountDownLatch bothReady =
            new CountDownLatch(2);

    private static final CountDownLatch keepAlive =
            new CountDownLatch(1);

    private static void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        Thread threadA = new Thread(() -> {

            synchronized (LOCK_A) {

                System.out.println(
                        "线程A：已经获得 LOCK_A"
                );

                bothReady.countDown();
                await(bothReady);

                System.out.println(
                        "线程A：尝试获取 LOCK_B"
                );

                synchronized (LOCK_B) {

                    System.out.println(
                            "线程A：获得两把锁"
                    );
                }
            }

        }, "线程A");


        Thread threadB = new Thread(() -> {

            synchronized (LOCK_A) {

                System.out.println(
                        "线程B：已经获得 LOCK_A"
                );

                bothReady.countDown();
                await(bothReady);

                System.out.println(
                        "线程B：尝试获取 LOCK_B"
                );

                synchronized (LOCK_B) {

                    System.out.println(
                            "线程B：获得两把锁"
                    );
                }
            }

        }, "线程B");


        threadA.start();
        threadB.start();

        // 故意让 main 保持运行，方便我们查看线程堆栈
        keepAlive.await();
    }
}
