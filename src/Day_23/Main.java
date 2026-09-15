package Day_23;
//线程安全与 synchronized，这正是以后后端并发、库存扣减、余额修改、抢票、线程池等问题的基础。
//先认识 join(),join() 可以简单理解成：当前线程等这个线程执行完，再继续。它会抛：InterruptedException
//--------------------------------------------------
//怎么解决线程安全？今天先学最经典的方法：synchronized
//例如原来：public void add() {count++;} 改成：public synchronized void add() {count++;}
//这时候 Java 会保证：同一时刻只能有一个线程进入这个方法。
//这样：count++;就不会被多个线程同时乱改。
//synchronized 到底锁了什么？对于：public synchronized void add() {count++}可以先理解成：锁住当前这个对象。
//也就是：this所以如果多个线程共享的是同一个：Counter counter那么它们会竞争同一把锁。
//--------------------------------------------------
//为什么“共享同一个对象”很重要？
//比如：Counter counter = new Counter();AddTask task = new AddTask(counter);Thread t1 = new Thread(task);Thread t2 = new Thread(task);
//这里：t1,t2->都操作同一个 counter所以会产生线程安全问题。如果每个线程都有自己的：Counter那它们互相不影响。
//所以线程安全问题通常需要满足：多个线程+共享同一份数据+至少一个线程会修改
//这三个条件很重要。
//---------------------------------------------------
//除了同步方法，还有同步代码块,有时候我们不想锁整个方法。
//可以：public void withdraw(int money) {synchronized (this) {if (balance >= money) { balance -= money;}}}
//这里：synchronized (this) {}表示：只有这一块代码需要加锁。
//---------------------------------------------------
//为什么不能把所有方法都加 synchronized？因为锁虽然安全，但也会降低并发能力。
//原本：线程线程A，线程B，线程C可以同时推进。
//加锁以后某些代码变成：A执行->B等待->C等待
//所以以后真正开发时不是：有线程就全部加锁。而是：只有访问共享可变数据的关键区域才考虑同步。
public class Main {

    public static void main() {
        Counter counter=new Counter();
        AddTask task=new AddTask(counter);
        Thread t1=new Thread(task,"线程1");
        Thread t2=new Thread(task,"线程2");
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        Account account=new Account();
        WithdrawTask task1=new WithdrawTask(account);
        Thread f1=new Thread(task1,"David");
        Thread f2=new Thread(task1,"Jack");
        f1.start();
        f2.start();
        try{
            f1.join();
            f2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }


        System.out.println(counter.count);
    }
}
