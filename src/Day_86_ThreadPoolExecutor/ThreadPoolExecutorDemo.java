package Day_86_ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args){
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(
                        2,
                        4,
                        10,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(2),
                        new ThreadPoolExecutor.AbortPolicy()
                );
        for(int i = 1;i<=6;i++){
            int taskId = i;
            executor.execute(()->{
                String taskName = Thread.currentThread().getName();
                System.out.println("线程"+taskName+"正在执行任务"+taskId);

                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}
//1.核心线程数量，最大线程数量 2.要保证执行顺序和避免资源浪费 3.队列满时，当线程池可以根据最大相乘数量来扩充当下的线程数量
//4.不会，因为当LinkedBlockingQueue无限大时，后来的任务都会进入队列等待
