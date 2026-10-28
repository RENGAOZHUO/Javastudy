import java.util.concurrent.CountDownLatch;

public class Day76RaceDemo {

    static class RequestCounter {

        private int unsafeCount = 0;

        private int safeCount = 0;

        private final Object lock = new Object();

        public void recordRequest() {

            // 不安全的递增
            unsafeCount++;

            // 加锁保护递增
            synchronized (lock) {
                safeCount++;
            }
        }

        public int getUnsafeCount() {
            return unsafeCount;
        }

        public int getSafeCount() {
            return safeCount;
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        int threadCount = 8;
        int loopCount = 100000;

        RequestCounter counter = new RequestCounter();

        Thread[] threads = new Thread[threadCount];

        CountDownLatch startGate =
                new CountDownLatch(1);

        for (int i = 0; i < threadCount; i++) {

            threads[i] = new Thread(() -> {

                try {
                    startGate.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                for (int j = 0; j < loopCount; j++) {
                    counter.recordRequest();
                }
1
            }, "worker-" + i);

            threads[i].start();
        }

        // 让工作线程开始执行
        startGate.countDown();

        // 等待所有线程结束
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(
                "理论结果：" + threadCount * loopCount
        );

        System.out.println(
                "未加锁结果：" + counter.getUnsafeCount()
        );

        System.out.println(
                "加锁结果：" + counter.getSafeCount()
        );
    }
}