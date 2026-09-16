package Day_24;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//线程池 ExecutorService
//前面创建线程一直是：Thread t1 = new Thread(task);Thread t2 = new Thread(task);t1.start();t2.start();
//这种方式学习线程原理很好，但真实后端项目通常不会每来一个任务就：new Thread(...)
//而是使用：线程池（Thread Pool）这也是以后 Spring、Web 请求处理、异步任务、高并发中非常重要的基础。
//为什么不能疯狂 new Thread()？假设服务器突然来了：10000 个请求。如果每个请求都：new Thread(...)那就意味着可能创建大量线程。但线程本身也需要消耗：内存，CPU调度资源，创建和销毁的时间
//所以现实中更合理的是：提前准备几个线程->任务来了就交给它们->线程执行完一个任务->继续执行下一个任务
//这就是线程池。
//-------------------------------------------
//Java 给我们提供：ExecutorService
//例如：ExecutorService pool =Executors.newFixedThreadPool(3);
//这里：3,表示：创建一个拥有 3 个工作线程的固定大小线程池。
//需要：import java.util.concurrent.ExecutorService;import java.util.concurrent.Executors;
//--------------------------------------------
//怎么把任务交给线程池？
//以前：Thread thread = new Thread(new MyTask());thread.start();
//今天：pool.submit(new MyTask());(MyTask()是一个连接了接口Runnable的类)
//也就是说：昨天：Runnable->Thread->start()
//今天：Runnable->线程池->submit()
//--------------------------------------------
//submit() 到底是什么意思？
//可以先把：pool.submit(task);理解成：把这个任务提交给线程池。
//不是你指定：必须线程1执行。而是线程池自己决定：谁现在有空->谁来执行
//例如：pool.submit(task1);pool.submit(task2);pool.submit(task3);pool.submit(task4);
//而线程池只有：3个线程
//可能变成：thread-1 → task1 thread-2 → task2 thread-3 → task3
//task4->暂时等待
//thread-2完成task2->thread-2继续执行task4
//这就是线程复用。
//---------------------------------------------
//shutdown() 是什么？
//pool.shutdown();意思不是：立刻把正在执行的线程全部杀掉。而是：不再接受新的任务，但是已经提交的任务继续执行完。
//例如：pool.submit(task1);pool.submit(task2);pool.shutdown();
//task1 和 task2 还是会正常执行。但之后不要再：pool.submit(task3);因为线程池已经开始关闭了。
//shutdown() 和 Day23 的 join() 不一样
//t1.join();表示：当前线程等 t1 执行结束。
//今天：pool.shutdown();表示：线程池不接受新任务了，处理完已有任务后关闭。它不是等待方法本身。
//---------------------------------------------
//为什么线程池比 new Thread() 更重要？
//以前：new Thread(task1).start();new Thread(task2).start();任务越多，创建的线程可能越多。
//线程池：ExecutorService pool =Executors.newFixedThreadPool(3);
//pool.submit(task1);pool.submit(task2);pool.submit(task3);pool.submit(task4);
//始终控制在一定数量的工作线程。
//所以核心价值就是：控制线程数量+重复利用线程+统一管理任务
//---------------------------------------------
//和后端有什么关系？以后你的服务器可能收到：请求A,请求B,请求c...
//或者需要：发邮件,生成报表,处理图片,异步写日志,执行后台任务
//这些地方都可能涉及线程池。
//所以以后面试经常会问：线程池为什么比直接 new Thread() 好？
//至少已经可以回答：1. 避免频繁创建和销毁线程.2. 可以复用线程.3. 可以限制线程数量.4. 方便统一管理任务
//----------------------------------------------
//一个很重要的关系:Day22：Runnable,你可能觉得只是为了学另一种创建线程的方法。现在你就能看到为什么它重要了。
//因为线程池需要的是：任务,而：Runnable正好就是任务。
//所以：Runnable->描述要做什么.ExecutorService->管理线程.submit()->把任务交给线程池
public class Main {
    public static  void main(String[] args){
        /*ExecutorService pool=Executors.newFixedThreadPool(2);
        for(int i=1;i<=6;i++){
            pool.submit(new MyTask(i));
        }
    pool.shutdown();*/
        ExecutorService pool=Executors.newFixedThreadPool(3);
        for(int i=1;i<=10;i++){
            pool.submit(new RequestTask(i));
        }
        pool.shutdown();
    }
}
//Day24 今天要记住
//ExecutorService
//→ 管理线程池
//
//Executors.newFixedThreadPool(3)
//→ 创建固定3个线程的线程池
//
//submit()
//→ 提交任务
//
//shutdown()
//→ 不再接受新任务，
//  已提交任务执行完成后关闭
//
//
//核心思想：
//
//不要：
//任务来了 → new Thread()
//
//而是：
//任务来了
//↓
//提交线程池
//↓
//已有线程重复执行任务
