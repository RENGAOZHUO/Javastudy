import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public static class  ResultBox {
    private int score;

    private boolean ready = false;

    private final ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void publish(int score){
        lock.lock();
        try{
            this.score = score;
            ready = true;
            System.out.println(Thread.currentThread().getName()+"成绩已发布 "+score);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public int awaitResult() throws InterruptedException {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"等待成绩");

            while(!ready){
                condition.await();
            }
            return score;
        }finally {
            lock.unlock();
        }
    }
}

public static void main(String[] args){
    ResultBox resultBox = new ResultBox();

    Thread threadA = new Thread(() ->
            resultBox.publish(95),"线程A"
    );

    Thread threadB = new Thread(()-> {try{
        int finalScore=resultBox.awaitResult();
        System.out.println(Thread.currentThread().getName()+"获得成绩 "+finalScore);
    }catch(InterruptedException e){
        Thread.currentThread().interrupt();
    }},"线程B");


    threadA.start();
    threadB.start();

}


