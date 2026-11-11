package Day_84;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SingleSlotBuffer {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private int value;
    private boolean hasValue = false;


    public void put(int value)
            throws InterruptedException{
        lock.lock();
        try{
            while(hasValue){
                notFull.await();
            }
            this.value=value;
            //System.out.println(Thread.currentThread().getName() + " 生产了: " + value);
            hasValue = true;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }

    }
    public int take()
            throws InterruptedException {
        lock.lock();
        int result;
        try {
            while (!hasValue) {
                notEmpty.await();
            }
            result = this.value;
            hasValue = false;
            notFull.signal();
        } finally {
            lock.unlock();
        }
        return result;
    }
    public static void main(String[] args){
        SingleSlotBuffer buffer = new SingleSlotBuffer();
        ArrayList<Integer> results = new ArrayList<>();

        Thread producer = new Thread(() ->{
                for(int i = 1;i<=5;i++){
                    try{
                        buffer.put(i);
                    }catch(InterruptedException e){
                        Thread.currentThread().interrupt(); // 恢复中断状态
                        System.out.println("生产者被中断，退出生产");
                        break; // 提示：恢复中断标志后，应退出当前循环
                    }
        }},"生产者");
        Thread consumer = new Thread(()->
        {
            for (int i = 1; i <= 5; i++) {
                try {
                     results.add(buffer.take());
                    //System.out.println(Thread.currentThread().getName() + " 消费了: " + result);
                } catch (InterruptedException e) {
                    // 推荐在捕获异常时恢复中断状态
                    Thread.currentThread().interrupt(); // 恢复中断状态
                    System.out.println("消费者被中断，退出消费");
                    break; // 提示：恢复中断标志后，应退出当前循环
                }
            }
        },"消费者");

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("主线程被中断");
        }

        System.out.println(results);
    }
}

