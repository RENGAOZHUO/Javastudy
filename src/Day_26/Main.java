package Day_26;

import Day_22.MyTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//Callable + Future —— 线程执行完，怎么拿到结果？
//run() 有一个明显限制：public void run()注意这里是：void
//也就是说：任务可以执行，但不能直接把计算结果 return 给调用者。
//假如以后线程负责：查询数据库、计算订单价格、统计用户数量、调用其他服务
//我们往往需要：“任务执行完成以后，把结果给我。”这就是今天的 Callable 和 Future。
//--------------------------------------
//今天认识：Callable，它和 Runnable 很像，但最大的区别是：Callable 可以返回结果。
//--------------------------------------------------
//Runnable 和 Callable 对比
//Runnable                      Callable
//返回 void                      可以返回结果
//不能直接返回计算结果              可以 return
//run() 不能声明普通受检异常         call() 可以抛异常
//---------------------------------------------------
//现阶段最重要的就是：
//Runnable->只需要“做事情”
//Callable->做完事情以后，我还要结果
//--------------------------------------
//但是 Callable 怎么交给线程池？
//昨天：pool.submit(new MyTask(1));
//今天也一样：pool.submit(new SumTask());
//但是今天非常重要的一点来了：submit()会返回一个东西：Future
//所以我们可以：Future<Integer> future =pool.submit(new SumTask());
//先把它理解成：SumTask->提交给线程池  线程池：“任务我拿去执行了”  ->返回 Future 给你
//这个：future可以理解成：一个代表“未来任务结果”的对象。
//---------------------------------------
//Future 不是结果本身
//假设：Future<Integer> future =pool.submit(new SumTask());
//此时线程池可能还没有计算完成。 所以：future.不是：30。它更像一张：“结果领取凭证”。
//等任务计算结束以后，通过：future.get()取得真正结果：Integer result = future.get();
//然后：System.out.println(result);得到：30
//整个过程就是：Callable任务->pool.submit()-> Future->future.get()->真正的结果
//--------------------------------------
//future.get() 到底发生了什么？
//任务完成了->get() 立即得到结果
//任务还没完成->get() 等待->等完成以后得到结果
//这种行为以后你会经常听到一个词：阻塞（blocking）,现在先理解成：当前线程在这里等着，暂时不能继续向下执行。
//--------------------------------------
//为什么 get() 会有两个异常？你会看到：catch (InterruptedException | ExecutionException e)
//InterruptedException 你已经见过。它表示：等待过程被中断。
//今天新认识：ExecutionException它表示：Callable 任务内部执行时发生了异常。
//真正执行任务的是线程池线程。
//异常不会简单地直接在 main 的 call() 那里出现，而是在你：future.get()
//获取结果时，以 ExecutionException 的形式告诉你：这个任务执行失败了。现在知道这个作用即可，不深入异常包装机制。
//--------------------------------------
//Future 还能判断任务完成没有，除了：get()还有：future.isDone()它返回：boolean
//例如：System.out.println(future.isDone());如果任务还没完成：false，完成以后：true
//--------------------------------------
//注意：Future 和 Thread 不是一个东西
//Thread表示：执行任务的线程。
//而：Future<Integer>表示：某个任务未来产生的结果。
//例如：线程池中的 thread-1->执行 SumTask->得到 5050->Future<Integer>->main通过 get() 获取5050;
//Future 不是线程。
//---------------------------------------
//ThreadPoolExecutor管理任务怎么执行,Callable + Future管理任务执行结果
public class Main {
    public static void main(){
        ExecutorService pool=Executors.newFixedThreadPool(2);
        Future<Integer> future=pool.submit(new SumTask(1,50));//Future<Integer>别忘了<Integer>
        Future<Integer> future1=pool.submit(new SumTask(51,100));
        try{

            Integer result=future.get();
            Integer result1=future1.get();
            int total=result+result1;
            System.out.println(total);
        }catch (InterruptedException|ExecutionException e){
            e.printStackTrace();
        }
        pool.shutdown();    }
}
