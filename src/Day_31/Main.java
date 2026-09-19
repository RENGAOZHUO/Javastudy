package Day_31;
//Optional + 方法引用
//-------------------------------------
//为什么需要 Optional？
//以前经常有这种代码：Student student = findStudent("Alice");
//但问题是：如果没找到 Alice 怎么办？过去很常见：return null;
//于是：Student student = findStudent("Bob");System.out.println(student.name);
//如果 student == null：NullPointerException这就是你以前学过的空指针异常。
//Optional<Student> 的思想是：我明确告诉你：这里可能有一个 Student，也可能没有。
//可以把它想成一个盒子：Optional<Student> 有：（Student）   无：（）
//-------------------------------------
//最基础：isPresent() + get()
//这里：isPresent()表示：里面有没有值？而：get()表示：把里面的值拿出来。
//但是：result.get();如果 Optional 是空的，会抛异常。所以不要随便裸写 get()。
//更推荐：ifPresent()如果你的需求只是：有就执行，没找到就算了。
//可以直接：result.ifPresent(student ->System.out.println(student.name));
//这句话非常值得理解。ifPresent() 接收的是：Consumer<Student>所以：student -> System.out.println(student.name)
//流程：Optional里有Student？->有->执行Lambda   没有->什么都不做
//所以原来：
//if (result.isPresent()) {
//    Student student = result.get();
//    System.out.println(student.name);
//}
//可以变成：
//result.ifPresent(
//        student -> System.out.println(student.name)
//);
//---------------------------------------
//如果没有值，我想给默认值呢？使用：orElse()
//例如：String name =
//        Optional.ofNullable(null)
//                .orElse("未知用户");
//结果：未知用户
//更贴近 Student：
//Student defaultStudent =
//        new Student("Unknown", 0, 0);
//
//Student student =
//        result.orElse(defaultStudent);意思：找到了就用找到的 Student；没找到就用默认 Student。
//orElseThrow()：找不到就报错，以后项目里这个非常常见。
//例如：Student student =
//        students.stream()
//                .filter(s ->
//                        s.name.equals("Alice"))
//                .findFirst()
//                .orElseThrow();
//意思：找到Alice→ 返回Student 没找到→ 抛异常
//以后 Spring 项目里你会经常看到类似：userRepository.findById(id)
//        .orElseThrow(...);所以 Optional 并不是为了考试，是后端里真的经常出现。
//----------------------------------------
//Optional.of() 和 ofNullable()，可以自己创建 Optional。
//例如：Student david =
//        new Student("David", 20, 92.5);
//Optional<Student> optional =
//        Optional.of(david);但是：Optional.of(null);会直接报错。
//如果变量可能为 null：Student student = null;
//Optional<Student> optional =
//        Optional.ofNullable(student);
//这时不会报错，而会得到：Optional.empty
//所以先简单记：of(value)→ 明确知道 value 不会是 null， ofNullable(value)→ value 可能是 null
//-----------------------------------------
//Optional 不应该被理解成“null 的高级写法”它真正的价值是：Student student = find(...);你看方法签名不知道：到底会不会返回 null？
//现在：Optional<Student> find(...)一看就知道：这个方法可能找不到结果。所以它是在方法设计层面表达“结果可能不存在”。
//-----------------------------------------
//接着学习今天第二部分：方法引用。Day27～Day30 写了很多：student ->System.out.println(student)
//有些 Lambda 特别简单，只是在调用已经存在的方法。这时候 Java 可以进一步简化。
//例如：students.forEach( student -> System.out.println(student));
//可以写成：students.forEach(System.out::println);
//这个：:: 就是：方法引用
//方法引用到底是什么？
//可以把：System.out::println理解成：“这里要执行的动作，就是调用 System.out.println()。”
//它并没有立即执行。只是把这个已有方法交给：forEach()
//所以：students.forEach(System.out::println);大致等价于：students.forEach(student -> System.out.println(student));
//再看一个你更熟悉的例子，以前：names.forEach(name -> System.out.println(name));
//可以：names.forEach(System.out::println);如果集合：David Jack Alice照样输出：David Jack Alice
//所以方法引用并不是另一套机制。它只是：当 Lambda 里面只是调用一个现成方法时，再进一步缩写。
//静态方法也能引用
//例如：Function<String, Integer> parser =text -> Integer.parseInt(text);
//这里 Lambda 只是调用：Integer.parseInt()所以可以写：Function<String, Integer> parser =Integer::parseInt;
//然后：int number = parser.apply("123");得到：123
//这两种写法你要能互相看懂，看到：Integer::parseInt你应该能脑补：text -> Integer.parseInt(text)
//看到：System.out::println脑补：value -> System.out.println(value)
//Student 也能用，假设给 Student 增加：public String getName() {return name;}
//那么以前：students.stream().map(student -> student.getName())
//可以：students.stream() .map(Student::getName)
//完整：students.stream().map(Student::getName).forEach(System.out::println);
//等价于：students.stream() .map(student -> student.getName()) .forEach(name -> System.out.println(name));
//你会发现 Stream 代码开始变得非常短。
//但不要为了短而硬用方法引用，例如：student -> student.score >= 60这种本身就是一个判断表达式，并没有一个现成的方法可以直接引用。
//那就正常写 Lambda：.filter(student ->student.score >= 60)不要为了使用 :: 强行改代码。可读性第一。
//------------------------------------------
//把 Day29～Day31 串起来
//现在你已经可以理解：
//students.stream()
//        .filter(student ->
//                student.score >= 60)
//        .map(Student::getName)
//        .forEach(System.out::println);
//整个流程：students->stream() Student->filter(Predicate<Student>)筛选及格 Student->map(Function<Student,String>)转换成名字
//String->forEach(Consumer<String>)打印Day28 的函数式接口、Day29 的 Stream、Day31 的方法引用，在这里全部连接起来。



import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args){
        ArrayList<Student> students=new ArrayList<>();
        students.add(new Student("David", 20, 92.5));//ArrayList使用add增加队列内容只能增加<>里的类型变量
        //增加一个类的新成员就需要类有构造函数，以及新建成员的语法
        students.add(new Student("Jack", 21, 58.0));
        students.add(new Student("Alice", 19, 95.5));
        students.add(new Student("Tom", 22, 61.0));
        /*Optional<Student> Alice=students.stream()
                .filter(student -> student.name.equals("Bob"))
                .findFirst();
        Student student=          //Optional<Student>  → 可能有 Student 的盒子
                Alice.orElse(     //orElse(默认Student) → 从盒子里取值，空了就使用默认对象
                        new Student("Unknown",0,0)
                );    //Student student    → 接收最终得到的 Student
        System.out.println(student.name);
        ArrayList<String> names=new ArrayList<>();
        names.add("David");names.add("Jack");names.add("Alice");
        names.stream()
                .forEach(System.out::println);*/
        students.stream()
                .filter(student -> student.score>=60)
                .map(Student::getName)//要调用自定义函数一定要是静态的，也就是static的
                .forEach(System.out::println);
    }
}
//Optional<T>
//→ 表示结果可能存在，也可能不存在
//
//isPresent()
//→ 是否有值
//
//ifPresent(...)
//→ 有值就执行
//
//orElse(...)
//→ 没有值就使用默认值
//
//orElseThrow()
//→ 没有值就抛异常
//
//ofNullable()
//→ 把可能为null的值包装成Optional
//
//
//方法引用 ::
//
//value -> System.out.println(value)
//≈
//System.out::println
//
//text -> Integer.parseInt(text)
//≈
//Integer::parseInt
//Student student = Alice.orElse(
//        new Student("Unknown", 0, 0)
//);新建的Student student是用来接受Alice这个Optional可能返回的Student或者新定义的new Student("Unknown", 0, 0)的
//其实它的意思非常简单：尝试从 Alice 这个 Optional 里取出 Student；如果没有，就创建一个默认学生；最后把得到的对象交给变量 student。
