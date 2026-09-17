package Day_28;


import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

//四大常用函数式接口:Consumer<T>、Supplier<T>、Function<T, R>、Predicate<T>
//Consumer  → 给我东西，我消费它
//Supplier  → 不给我东西，我生产一个东西
//Function  → 给我一个东西，我转换成另一个东西
//Predicate → 给我一个东西，我判断 true / false
//-------------------------
//为什么还要学习这些接口？现实开发中，有很多非常常见的需求，例如：
//打印一个值、产生一个值、转换一个值、判断一个值
//如果每次都自己创建函数式接口，就很麻烦，所以 Java 已经帮我们定义好了通用接口。今天就是认识它们。
//-------------------------
//Consumer：消费一个东西
//Consumer<String>可以理解成：给我一个 String，我要拿它做点什么，但是不返回结果。
//例如：Consumer<String> consumer =name -> System.out.println(name);
//使用：consumer.accept("David");输出：David
//这里很重要：accept()就是 Consumer 接口里的那个抽象方法。
//它大概可以理解为：void accept(T value);
//所以：Consumer<String> consumer =name -> System.out.println(name);
//其实是在实现：void accept(String name) {System.out.println(name);}
//Lambda 只是把它缩短了。
//-------------------------
//Supplier：生产一个东西
//例如：Supplier<String> supplier = () -> "David";
//调用String name = supplier.get();得到：David
//注意 Lambda：() -> "David"这里：()说明：不需要参数。而"David"是返回值。
//Supplier 的核心方法可以理解成：T get();所以：Supplier<String>
//表示：不输入任何东西->给我一个 String
//-------------------------
//Function：转换这个以后非常常见。
//例如：Function<String, Integer> function =text -> text.length();
//使用：Integer result = function.apply("David");结果：5
//这里：Function<String, Integer>有两个类型：String   → 输入类型,Integer  → 返回类型
//所以：String → Function → Integer可以理解为：给我一个 String，我把它转换成 Integer。
//核心方法是：R apply(T value);所以：function.apply("David")就是调用 Lambda 实现的功能。
//再举一个更直观的例子Function<Integer, Integer> doubleNumber =number -> number * 2;
//调用：int result = doubleNumber.apply(10);得到：20
//流程：10->number -> number * 2->20
//所以 Function 最容易记：输入一个东西，处理以后返回另一个东西。
//---------------------------
//Predicate：判断
//第四个是：Predicate<T>它特别重要，因为后面 Stream 的：filter()大量使用 Predicate。
//例如：Predicate<Integer> predicate =number -> number >= 60;
//调用：boolean result =predicate.test(80);得到：true,如果：predicate.test(50);得到：false
//Predicate 的核心方法可以理解为：boolean test(T value);
//所以：输入一个 T->进行判断->true / false
//Predicate 其实就是把这种：student.score >= 60“判断规则”包装成了一个对象。
//以前你的判断规则只能直接写在：if (...) {}里面。比如：if (student.score >= 60) {..}
//现在可以：Predicate<Student> passed =student -> student.score >= 60;
//也就是：把“成绩 >= 60”这个判断规则本身保存进变量 passed。
//这就是 Lambda / 函数式接口真正强大的地方：不仅数据可以放进变量，行为和规则也可以被传递。
//---------------------------
//四个接口放一起看
//这张表今天非常重要：
//接口              输入        输出      核心方法
//Consumer<T>       T           无       accept()
//Supplier<T>       无           T       get()
//Function<T,R>     T            R       apply()
//Predicate<T>      T           boolean  test()
//你可以更简单地记：
//Consumer吃进去，不吐出来.   Supplier不吃，吐出来
//Function吃进去，变一个东西吐出来.   Predicate吃进去，只回答 true / false
//----------------------------
//
public class Main {
    public static void main(String[] args){
        Consumer<String> printer=
                String->System.out.println(String);
        printer.accept("Hello Java");
        Function<String,Integer> length=
                text->text.length();
        System.out.println(length.apply("Hello"));
        Student david =
                new Student("David",20,92.5);
        Student tom =
                new Student("Tom",20,55.0);
        Predicate<Student> passed =    //passed相当于之前为了实现接口方法构建的类的成员
                student->student.score>=60;//这里真正决定 student 类型的是：Predicate<Student><Student>告诉Java之后的变量是Student类型的
        System.out.println(passed.test(david));//test相当于类为接口重新定义的方法
        System.out.println(passed.test(tom));
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("David", 20, 92.5));//ArrayList使用add增加队列内容只能增加<>里的类型变量
        //增加一个类的新成员就需要类有构造函数，以及新建成员的语法
        students.add(new Student("Jack", 21, 58.0));
        students.add(new Student("Alice", 19, 95.5));
        students.add(new Student("Tom", 22, 61.0));
        for (Student student : students) {

            if (passed.test(student)) {

                System.out.println(
                        student.name
                                + " 通过考试"
                );
            }
        }
        Function<Student,String> getName =
                student -> student.name;
        System.out.println(getName.apply(david));
        Consumer<Student> printStudent =
                student -> System.out.println(student.name+student.age+student.score);
        printStudent.accept(david);

        //四大函数式接口：
        //
        //Consumer<T>
        //T → 无返回
        //accept()
        //
        //Supplier<T>
        //无参数 → T
        //get()
        //
        //Function<T,R>
        //T → R
        //apply()
        //
        //Predicate<T>
        //T → boolean
        //test()
        //再加一句最重要的：函数式接口让我们可以把“行为/规则”像数据一样保存在变量里，再通过 Lambda 提供具体实现



    }
}
