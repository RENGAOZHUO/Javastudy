package Day_78;

public class Day78PublishDemo {

    private static String studentName;
    private static int studentScore;

    private static volatile boolean ready = false;

    public static void main(String[] args)
            throws InterruptedException {

        Thread reader = new Thread(() -> {

            // 等待学生信息准备完成
            while (!ready) {
                Thread.onSpinWait();
            }

            System.out.println(
                    "学生：" + studentName
                            + "，成绩：" + studentScore
            );
        });

        reader.start();

        // main 线程准备学生信息
        studentName = "张三";
        studentScore = 95;

        // 必须在数据准备好以后发布信号
        ready = true;

        reader.join();
    }
}
