package Day_22;
//Thread.currentThread().getName().Thread.currentThread()表示：当前正在执行这段代码的线程。.getName()：获取线程名称。
//怎么创建自己的线程？1.继承 Thread，2.实现 Runnable
//一个很重要的方法：run()可以简单理解成：这个线程启动之后要执行什么任务。
//线程不是“同时执行”这么简单
//___现在先建立一个稍微准确一点的概念：并发 concurrency可以简单理解：多个任务在一段时间内交替推进。
//___例如一个 CPU 核：线程A → 执行一会->线程B → 执行一会->线程A → 再执行->线程B → 再执行
//___速度非常快，所以人感觉好像：同时在运行,如果有多个 CPU 核心，还可能真的存在多个线程同时执行，这叫并行。
//___现在只需要大致区分：并发：多个任务一起推进;并行：多个任务真正同时执行
//注意：Runnable是一个接口。必须实现：run()
//Runnable 本身不是线程
//MyTask task = new MyTask();创建的是：一个任务。它还不是 Thread。
//所以要：Thread t = new Thread(task);再：t.start();
//关系可以画成：MyTask->描述“要做什么”;Thread->负责真正执行任务
//也就是：Runnable = 任务,Thread = 执行任务的线程
//为什么更推荐 Runnable？假设：class MyThread extends Thread，你的类已经：继承 Thread，Java 又不支持：一个类同时继承两个普通父类，所以继承能力被占掉了。
//但是：class MyTask implements Runnable只是在：实现一个接口以后还可以继承其他类。而且更重要的是：任务和线程分开了。
//比如：MyTask task = new MyTask();同一个任务甚至可以交给不同线程：Thread t1 = new Thread(task);Thread t2 = new Thread(task);
//--------------------------------------------------------------------
//给线程起名字Thread t1 = new Thread(task);t1.setName("线程A");t1.start();
//然后 run()：System.out.println(Thread.currentThread().getName());就可以看到：线程A
//也可以创建时：Thread t1 =new Thread(task, "线程A");
//---------------------------------------------------------------------
//认识 sleep()例如：Thread.sleep(1000);也就是：1秒
//eg.for (int i = 1; i <= 5; i++) {System.out.println(i);Thread.sleep(1000);}大约每隔一秒输出一次。
//不过它可能抛：InterruptedException
//所以可以：try {Thread.sleep(1000);} catch (InterruptedException e) { e.printStackTrace();}
//
public class Main {
    public static void main(String[] args){
        /*MyThread t = new MyThread();//第一种方法：Thread

        t.start();//这里最重要：为什么是 start()，不是 run()？
        //如果·t.run();本质上只是：main 线程像调用普通方法一样调用 run()。并没有真正创建新的执行线程。
        //而t.start();才是：告诉 JVM 启动一个新的线程，然后由这个新线程执行 run()。
        for (int i = 0; i < 5; i++) {

            System.out.println(
                    "main线程：" + i
            );}//为什么输出是main线程以及子线程？以及为什么每次执行先后顺序都不一样？
        //因为：main线程+MyThread线程，都在运行。到底谁先执行一小段，不能简单认为永远固定。*/
        /*MyTask task = new MyTask();

        Thread f = new Thread(task,"线程名");
        Thread u = new Thread(task,"未定义姓名");

        f.start();
        u.start();*/
        Thread downloadThread = new Thread(new DownloadTask(),"下载线程");
        downloadThread.start();
        for (int i = 1; i <= 5; i++) {
            System.out.println("main线程正在做其他事情：" + i);
        }

    }
}

//Thread
//→ 线程
//
//run()
//→ 线程需要完成的任务
//
//start()
//→ 真正启动新线程
//
//不要用 run() 代替 start()
//
//
//方式1：
//extends Thread
//
//方式2：
//implements Runnable
//
//Runnable
//→ 描述任务
//
//Thread
//→ 执行任务
//
//
//Thread.currentThread()
//→ 当前线程
//
//getName()
//→ 获取线程名
//
//Thread.sleep()
//→ 让当前线程暂时休眠
