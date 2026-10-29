package Day_76;

public class Day76ThreadDemo {
    public static void main(String[] args)
            throws InterruptedException {

        Thread worker = new Thread(() -> {
            System.out.println(
                    Thread.currentThread().getName()
                            + "：正在执行任务"
            );
        }, "worker-1");

        System.out.println("启动前：" + worker.getState());

        worker.start();

        worker.join();

        System.out.println("结束后：" + worker.getState());
        System.out.println("main：继续执行");
    }
}
