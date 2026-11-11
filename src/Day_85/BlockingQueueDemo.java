package Day_85;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args){
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
        ArrayList<Integer> results = new ArrayList<>();

        Thread producer = new Thread(()->
        {
            for(int i = 1 ; i <= 5 ; i++){
                try{
                    queue.put(i);
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        },"生产者");

        Thread consumer = new Thread(()->{
            for(int i = 1;i <= 5;i++){
                try{
                    results.add(queue.take());
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        },"消费者");

        producer.start();
        consumer.start();

        try{
            producer.join();
            consumer.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }

        System.out.println(results);
    }
}
//1.put会等待队列数据不满时再放入新数据
//2.take()会等待队列数据满是再取出数据
//3.因为它是一种支持阻塞操作的队列。有先进先出的特点，先放进去的数据通常先取出来。阻塞又使如果暂时无法放入或取出数据，线程可以等待，而不是不断检查。
