package basicstrength.process_and_thread;

public class ThreadDemo {public static void main(String[] args) throws InterruptedException {

    Thread worker = new Thread(() -> {
        for (int i = 1; i <= 5; i++) {
            System.out.println(
                    Thread.currentThread().getName() + "：任务 " + i
            );
        }
    }, "工作线程");

    worker.run();

    for (int i = 1; i <= 5; i++) {
        System.out.println(
                Thread.currentThread().getName() + "：任务 " + i
        );
    }

    worker.join();

    System.out.println("两个线程的打印任务都已完成");
}
}
