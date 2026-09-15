package Day_23;

public class Account {
    int balance = 1000;
    public synchronized void withdraw(int money){
        if(money<1000){
            System.out.println(Thread.currentThread().getName()+"正在取款");
            balance-=money;
            System.out.println(Thread.currentThread().getName()+"取款成功，余额："+balance);
        }
        else{
            System.out.println(Thread.currentThread().getName()+"取款失败,余额不足！");
        }
    }
}
