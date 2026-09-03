package Day_4;
class BankAccount{
    private String name;
    private double balance;

    BankAccount(String name,double balance){
        this.name=name;
        this.balance=balance;
    }

    public void deposit(double ammount){
        balance+=ammount;
    }

    public void withdraw(double ammount){
        if(balance>0&&ammount<=balance){
            balance-=ammount;
        }else{
            System.out.println("取款失败！");
        }
    }

    public void transfer(BankAccount target,double ammount){
        if(this.balance>=ammount){
            this.balance-=ammount;
            target.balance+=ammount;
        }else{
            System.out.println("转账失败！");
        }
    }
    public String getName(){
        return name;
    }
    public double getBalance(){
        return balance;
    }
}
public class day_4 {

    public static void main(String[] args){
    BankAccount tom=new BankAccount("tom",3000);
    BankAccount jack=new BankAccount("jack",1000);
    System.out.println(tom.getName());
    System.out.println(tom.getBalance());
    System.out.println(jack.getName());
    System.out.println(jack.getBalance());
    tom.transfer(jack,500);
        System.out.println(tom.getName());
        System.out.println(tom.getBalance());
        System.out.println(jack.getName());
        System.out.println(jack.getBalance());
    }
}
