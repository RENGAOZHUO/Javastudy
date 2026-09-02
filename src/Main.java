//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
import java.util.Scanner;
class  Student{
    String name;
    int age;
    double score;

    Student(String name,int age,double score){
        this.name=name;
        this.age=age;
        this.score=score;
    }

    void introduce(){
        System.out.println("我是"+name+
                "今年"+age+
                "岁，成绩是"+score);
    }
        }

    class  BankAccount{
    String accname;
    double balance;

    BankAccount(String accname,double balance){
        this.accname=accname;
        this.balance=balance;
    }
    void deposit(double amount){
        System.out.println("存入："+amount);
        balance+=amount;
        System.out.println("余额"+balance);
    }
    void withdraw(double amount){
        System.out.println("取出"+amount);
        balance-=amount;
        if(balance>=0){
            System.out.println("余额"+balance);
        }else{
            System.out.println("余额不足！");
        }
    }
    void show(){
        System.out.println("用户："+accname);
        System.out.println("余额"+balance);
    }
    }
public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       Student student1 = new Student("景甜",38,5000.00);
       Student student2 = new Student("张继科",37,100.01);
       student1.introduce();;
       student2.introduce();
       BankAccount bankaccount1=new BankAccount("Tom",2000);
       bankaccount1.show();
       bankaccount1.deposit(500);
       bankaccount1.withdraw(1000);
        }
    }
