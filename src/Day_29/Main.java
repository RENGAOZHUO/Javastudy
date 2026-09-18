package Day_29;

import java.util.ArrayList;
import java.util.List;

//Stream API 入门
//先理解一条很重要的处理思路：把集合中的数据送进一条处理流水线，然后依次做筛选、转换和最终处理。
//students.stream()
//        .filter(student -> student.score >= 60)
//        .forEach(student -> System.out.println(student.name));
//上面的工作流是在描述“这批数据应该经过哪些处理”。
//先看第一步：students.stream()
//这里并不是又创建了一个新的 ArrayList。你可以先把它理解成：把 students 里的元素交给 Stream，让这些元素开始进入一条处理链。
//原来的集合：ArrayList<Student>
//经过：students.stream()以后，可以先想象成：
//David → Jack → Alice → Tom
//真正有用的是后面的处理方法。其中最先要掌握的是：filter()它负责“筛选”。
//比如：students.stream() .filter(student -> student.score >= 60)
//这里：student -> student.score >= 60其实就是你 Day28 学过的：Predicate<Student>
//因为它的特点是：输入 Student->返回 boolean
//每个学生都会被拿来判断一次：
//所以经过 filter() 以后，这条流里继续向后走的，只剩：David,Alice,Tom
//可以直接把：filter记成：筛选
//筛选完以后，通常还要对剩下的数据做点事情，这时就会用：forEach()
//例如：.forEach(student ->System.out.println(student.name));
//所以这一整段：
//students.stream()
//        .filter(student -> student.score >= 60)
//        .forEach(student -> System.out.println(student.name));
//可以直接理解成：学生集合->变成 Stream->留下及格学生->把这些学生逐个打印
//---------------------------------
//今天第二个核心方法是：map()
//filter() 回答的问题是：这个元素要不要留下？
//而 map() 回答的是：这个元素要变成什么？
//比如现在流里装的是：Student
//但我们只想保留学生姓名：
//students.stream()
//        .map(student -> student.name)
//        .forEach(name ->
//                System.out.println(name));
//这里：student -> student.name正好就是：Function<Student, String>
//所以可以把：Stream<Student>里的对象，经过：map(student -> student.name)理解成：Stream<String>
//因此 map() 最重要的含义就是：转换流中元素的形式。
//-----------------------------------
//filter() 和 map() 很容易混，所以今天一定要分清：
//student -> student.score >= 60返回的是：boolean它是在判断“留不留”，所以属于：filter()
//而：student -> student.name返回的是：String它是在把一个 Student 转换成一个 String，所以属于：map()
//也就是说：filter Student → boolean 决定留不留
//map Student → String 把它变成别的东西
//这正好对应 Day28：filter → Predicate  map → Function  forEach → Consumer
//----------------------------------
//Stream 还有一个非常重要的特点：它通常不会因为你做了筛选，就直接修改原来的集合。
//如果你想把筛选后的结果真正保存到一个新的集合里，可以使用：
//List<Student> passedStudents =
//        students.stream()
//                .filter(student -> student.score >= 60)
//                .toList();
//这里因为用到了：List<Student>所以需要：import java.util.List;
//-----------------------------------
//还有一个很简单但很实用的方法：count()，例如统计有多少学生及格：
//long count =
//        students.stream()
//                .filter(student -> student.score >= 60)
//                .count();
//这里注意：count()返回的是：long，所以写：long count更合适。
//------------------------------------
//今天还需要认识一个概念，但不用死背术语。像：filter() map()
//这种方法执行以后，还可以继续接：.filter(...) .map(...) .filter(...)它们通常叫“中间操作”。
//而：forEach() count() toList()这种会得到最终结果或者真正触发处理的，可以先理解成“终止操作”。
//比如：
//students.stream()
//        .filter(...)
//        .map(...)
//        .forEach(...);
//可以理解成：创建流->中间处理->中间处理中间处理->最终执行
//这里还有一个 Stream 初学时非常容易困惑的现象。
//如果你只写：
//students.stream()
//        .filter(student -> {
//            System.out.println(student.name);
//            return student.score >= 60;
//        });
//你可能会发现：什么都没打印
//原因是很多 Stream 的中间操作是“惰性执行”的。简单理解就是：你只是告诉 Stream“以后如果要处理数据，就这样筛选”，但还没有真正要求它给出结果。
//如果后面加上：
//.forEach(student ->
//        System.out.println(student.name));
//整条流水线才真正开始执行。所以目前可以记住：Stream 往往需要 forEach()、count()、toList() 这样的终止操作，前面的处理才真正发生。
//另外，不要把 Stream 理解成“以后 for 循环就没用了”。简单逻辑时，普通 for 很可能更直观；当你需要连续完成：
//筛选
//→ 转换
//→ 排序
//→ 收集
//这一类多步处理时，Stream 往往表达得更清晰。
public class Main {
    public static void main(){
        ArrayList<Student> students=new ArrayList<>();
        students.add(new Student("David",20,92.5));
        students.add(new Student("Jack",21,58.0));
        students.add(new Student("Alice",19,95.5));
        students.add(new Student("Tom",22,61.0));
        List<Student> passedStudents=
                students.stream()
                        .filter(student -> student.score>=60)
                        .toList();
        passedStudents.forEach(
                student -> System.out.println(student.name)
        );
        System.out.println(students.size());
        System.out.println(passedStudents.size());

    }
}

//Stream：
//
//filter(Predicate)
//→ 筛选
//
//map(Function)
//→ 转换
//
//forEach(Consumer)
//→ 对每个元素执行操作
//
//count()
//→ 统计
//
//toList()
//→ 收集成 List
//
//Stream 操作通常不会修改原集合。
