package Day_77;

public class Day77PublishDemo {

    private static int score = 0;

    private static volatile boolean ready = false;

    public static void main(String[] args)
            throws InterruptedException {

        Thread reader = new Thread(() -> {

            while (!ready) {
                Thread.onSpinWait();
            }

            System.out.println("读取成绩：" + score);

        });

        reader.start();

        // main 准备数据
        score = 95;

        // 最后通知：数据准备好了
        ready = true;

        reader.join();
    }
}
