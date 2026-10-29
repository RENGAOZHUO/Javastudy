package Day_77;

public class Day77VisibilityDemo {

    private static volatile boolean running = true;

    public static void main(String[] args)
            throws InterruptedException {

        Thread worker = new Thread(() -> {

            System.out.println("worker：开始工作");

            while (running) {
                Thread.onSpinWait();
            }

            System.out.println("worker：收到停止信号");

        }, "worker");

        worker.setDaemon(true);

        worker.start();

        // 给工作线程一段运行时间
        Thread.sleep(500);

        // main 修改共享变量
        running = false;

        System.out.println("main：已发送停止信号");

        worker.join(2000);

        System.out.println(
                "worker 是否仍在运行：" + worker.isAlive()
        );
    }
}
