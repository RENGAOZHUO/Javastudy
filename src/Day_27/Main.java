package Day_27;
//Lambda 表达式 + 函数式接口
//以前创建一个线程任务，如果这个任务只用一次，单独创建MyTask.java显得有点麻烦。
//所以有：
//Runnable task = new Runnable() {
// @Override
// public void run() {
//        System.out.println("任务执行");
//}}
//这个叫：匿名内部类。
//意思就是：我不专门创建一个 MyTask 类了，直接在这里临时创建一个实现 Runnable 的对象。
//Lambda 就是在继续简化它，上面的零食声明，可以简化为：
//Runnable task = () -> {
//    System.out.println("任务执行");
//}；
//再短一点：Runnable task =() -> System.out.println("任务执行");
//这就是：() -> ...Lambda 表达式。
//----------------------------------
//() 和 -> 到底是什么意思？
//例如：() -> {System.out.println("Hello");}
//可以先理解为：()->这个方法没有参数
//->
//{
//    方法要执行的代码
//}
//因为 Runnable 要求的方法本来就是：void run()它：没有参数,没有返回值
//所以 Lambda 是：() -> {  //run里面的代码}
//可以把：Runnable task = () -> { System.out.println("Hello");};
//粗略理解成：“这里给你一个 Runnable 对象，它的 run() 要做的事情就是打印 Hello。”
//------------------------------------
//为什么 Java 知道这是 run()？
//因为：Runnable接口里面只有一个需要实现的抽象方法：void run();
//所以 Java 看见：Runnable task = ...就知道：Lambda 描述的一定是 run() 的实现。
//-------------------------------------
//什么是函数式接口？
//像：Runnable这种只有一个抽象方法的接口，可以用于 Lambda。
//这种接口叫：函数式接口 Functional Interface
//比如：Runnable核心抽象方法只有：void run();所以：Runnable task = () -> System.out.println("执行");成立
//--------------------------------------
//Callable 也可以使用 Lambda
//Day26 刚刚写过：public class SumTask implements Callable<Integer> {@Override public Integer call() {return 100;}}
//其实也可以：Callable<Integer> task = () -> { return 100;};
//因为：Callable<Integer>核心抽象方法就是：Integer call()
//所以 Lambda：() -> {return 100;}
//就相当于实现：call()
//甚至可以更短，如果只有：return 100;可以：Callable<Integer> task = () -> 100;
//注意：() -> 100，这里 Java 会自动把 100 作为返回值。相当于：() -> {return 100;}
//----------------------------------------
//Lambda 的基本结构(参数) -> {    方法体}
//例如：没参数、没返回值() -> {    System.out.println("Hello");}
//一个参数name -> {    System.out.println(name);}
//两个参数(a, b) -> {    return a + b;}
//两个参数 + 简单返回(a, b) -> a + b
//-----------------------------------------
//Lambda 不是“线程专属语法”
//Lambda 是 Java 的一种语言语法，主要用来：简洁地提供函数式接口的实现。
//所以它可以用于：线程、集合、Stream、事件处理、异步任务、很多框架API
//------------------------------------------
//什么时候不能用 Lambda？
//假设接口：它有两个抽象方法。那你就不能简单：Test t = () -> ...;
//因为 Java 不知道：你到底是在实现 a() 还是 b()？所以 Lambda 的前提：目标接口只有一个抽象方法。
//-------------------------------------------
//认识 @FunctionalInterface
//有时候会看到：
//@FunctionalInterface
//public interface Calculator {
//    int calculate(int a, int b);}
//这个注解的意思：我希望这个接口是一个函数式接口。
//如果你后来又写：void anotherMethod();IDE / 编译器就会提醒你：现在不再满足函数式接口要求了。今天知道即可。

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main() throws ExecutionException, InterruptedException {
        Runnable task = () -> System.out.println(Thread.currentThread().getName() + "正在执行");
        Thread t1 = new Thread(task, "线程A");
        t1.start();
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<Integer> future = pool.submit(() -> {
            int sum = 0;                  //这里：pool.submit(...)
            for (int i = 1; i <= 100; i++) {    //接收一个可以执行的任务。
                sum += i;               //因为这个 Lambda 最终有：return sum;
            }                   //Java 可以匹配到：Callable<Integer>然后返回：Future<Integer>
            return sum;     //所以整个关系依然是：
        });    //Lambda->Callable<Integer>->线程池执行->Future<Integer>
        int result = future.get();
        System.out.println(result);
        pool.shutdown();
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("David", 20, 92.5));//ArrayList使用add增加队列内容只能增加<>里的类型变量
        //增加一个类的新成员就需要类有构造函数，以及新建成员的语法
        students.add(new Student("Jack", 21, 88.0));
        students.add(new Student("Alice", 19, 95.5));
        students.forEach(
                student->{
                    System.out.println("姓名"+student.name);
                    System.out.println("年龄"+student.age);
                    System.out.println("成绩"+student.score);
                }
        );
        Calculartor add=(a,b)->a+b;
        Calculartor subtract=(a,b)->a-b;
        System.out.println(add.calculartor(10,5));
        System.out.println(subtract.calculartor(10,5));
    }
}
