package Day_25;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//ThreadPoolExecutor 线程池核心原理
//今天的目标不是背一堆参数，而是弄懂一个最重要的问题：
//任务提交进线程池之后，到底按照什么顺序被处理？
//昨天你写：ExecutorService pool = Executors.newFixedThreadpool(3);然后：pool.submit(task);
//但实际上：newFixedThreadPool(3)内部最终依赖的是：ThreadPoolExecutor
//所以今天相当于：把昨天那个“黑盒子”打开一点。
//先认识 ThreadPoolExecutor它是真正用来创建线程池的核心类之一。
//最重要：任务到底按什么顺序处理？不是核心线程一满，就马上创建非核心线程。
//而是：先核心线程->再进队列->队列也满了->才增加线程
//如果：核心线程：满，队列：满。最大线程数：也满
//这就进入：拒绝策略
//----------------------------
//线程池处理任务的核心流程
//新任务来了->核心线程没满？是->创建/使用核心线程执行 否->队列还能放？是->进入队列等待 否->线程数还没到 maximumPoolSize？是->创建额外线程执行 否->执行拒绝策略
//----------------------------
//为什么不一开始就创建 maximumPoolSize 个线程？
//比如：core = 2,max = 100如果只是来了两个任务，没必要直接创建：100个线程,这会浪费资源。
//所以：corePoolSize代表比较稳定的线程数量。而：maximumPoolSize像高峰期允许临时扩容的上限。
//-----------------------------
//keepAliveTime 是什么？
//60,TimeUnit.SECONDS
//组合起来：60秒,表示：多出来的非核心线程，空闲太久以后可以被回收。
//比如：核心线程：2个,高峰时临时扩成：4个.后来任务少了。线程3、线程4一直没事干。超过：60秒
//它们可能被销毁。最终重新回到：2个核心线程
//所以：keepAliveTime可以理解成：临时线程空闲多久以后离开。
//------------------------------
//认识拒绝策略
//当：线程满了+队列满了，新任务就放不进去了。这时候必须决定：新任务怎么办？
//最经典的一个：new ThreadPoolExecutor.AbortPolicy()意思：直接拒绝任务并抛异常。
//如果继续提交过多任务：RejectedExecutionException就可能出现。
//还有其他拒绝策略,今天只认识名字，不需要背细节。
//常见：AbortPolicy->拒绝并抛异常,CallerRunsPolicy->让提交任务的线程自己执行,DiscardPolicy->直接丢弃任务,DiscardOldestPolicy->丢掉队列里较老的任务，再尝试提交
//现在最重要的是知道：线程池容量不是无限的。这和后端非常重要。
//-------------------------------
//Day24 和 Day25 的区别
//Day24：Executors.newFixedThreadPool(3)
//重点：会使用线程池,理解线程复用
//Day25：new ThreadPoolExecutor(...)
//重点：线程池内部怎么决定：直接执行？排队？增加线程？拒绝？
//--------------------------------
//后端面试最常问的一句话
//以后别人问：ThreadPoolExecutor 提交任务时的执行流程是什么？
//你至少应该能说：
//先看核心线程是否已满；没满就创建核心线程执行。核心线程满了以后，任务先进入工作队列；如果队列也满了，再尝试创建非核心线程，
//直到最大线程数。如果线程数达到最大值且队列也满，则执行拒绝策略。
public class Main {
    public static void main(){
        /*ThreadPoolExecutor pool =
                new ThreadPoolExecutor(
                        2,//corePoolSize = 2也就是：核心线程数。
                        4,//maximumPoolSize = 4也就是：线程池最多允许有多少个线程。
                        60,
                        TimeUnit.SECONDS,//表示：多出来的非核心线程，空闲太久以后可以被回收。
                        new ArrayBlockingQueue<>(2),//这里：new ArrayBlockingQueue<>(2)
                        //表示创建一个：最多可以等待2个任务的队列。
                        new ThreadPoolExecutor.AbortPolicy()
                );*/
        ThreadPoolExecutor pool=
                new ThreadPoolExecutor(
                      2,
                      5,
                        60,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(3),
                        new ThreadPoolExecutor.AbortPolicy()
                );
        for(int id=1;id<=9;id++){
            pool.submit(new MyTask(id));
        }
        pool.shutdown();
    }
}
