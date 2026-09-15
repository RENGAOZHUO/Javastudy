package Day_23;

public class WithdrawTask implements Runnable{
    Account account=new Account();
    public WithdrawTask(Account account){
        this.account=account;
    }
    @Override
    public void run(){
        account.withdraw(800);
    }
}
