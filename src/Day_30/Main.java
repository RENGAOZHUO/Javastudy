package Day_30;

import java.util.ArrayList;
import java.util.Optional;

//Stream 进阶——排序、截取、查找与匹配
//今天主要掌握：
//sorted() limit() skip()
//anyMatch() allMatch() noneMatch()
//findFirst()
//max() min()
//其中最重要的是 排序 + 条件判断 + 查找。
//-----------------------------
//sorted()：排序
//如果只是：students.stream().sorted()Java 会面临一个问题：Student 到底按照什么排序？是：姓名？年龄？成绩？
//Java不知道。所以我们要告诉它比较规则。例如：
//students.stream()
//        .sorted((s1, s2) ->
//                Double.compare(s1.score, s2.score))
//        .forEach(student ->
//                System.out.println(
//                        student.name + " " + student.score
//                ));
//这样是：按照成绩从小到大排序。
//这里的 Lambda 到底是什么？
//这一段：(s1, s2) -> Double.compare(s1.score, s2.score)其实是在提供：Comparator<Student>所需要的比较规则。
//可以先理解：拿两个 Student：s1 s2->比较二者成绩->告诉 sorted 谁应该排前面
//降序怎么办？例如希望最高分排第一：
//可以交换比较顺序：.sorted((s1, s2) ->Double.compare(s2.score, s1.score))
//注意：升序：s1.score, s2.score 降序：s2.score, s1.score现在先这样理解即可。以后我们还会看到更漂亮的：Comparator.comparingDouble(...)
//--------------------------------
//limit()：只要前几个
//假设你已经按照成绩降序：
//students.stream()
//        .sorted((s1, s2) ->
//                Double.compare(s2.score, s1.score))
//如果只想要：成绩最高的两个学生.继续：limit(2)
//完整：
//students.stream()
//        .sorted((s1, s2) ->
//                Double.compare(s2.score, s1.score))
//        .limit(2)
//        .forEach(student ->
//                System.out.println(student.name));
//输出：Alice David所以：limit(2)就是：最多只让前 2 个元素继续向后走。
//-----------------------------------
//skip()：跳过前几个
//例如：students.stream().skip(2)
//表示：跳过前两个元素。
//如果原始顺序：David Jack Alice Tom那么：.skip(2)以后剩：Alice Tom
//------------------------------------
//skip() + limit() 很重要,以后分页会和这种思想很像。
//比如：students.stream()
//        .skip(1)
//        .limit(2)
//表示：先跳过1个->然后取2个 得到：Jack Alice
//以后学数据库分页：limit ...你会发现思想很接近。
//-------------------------------------
//anyMatch()：有没有一个满足条件？
//现在问.有没有成绩大于 90 的学生？
//以前：boolean found = false;
//
//for (Student student : students) {
//    if (student.score > 90) {
//        found = true;
//        break;
//    }
//}
//现在 Stream 可以：
//boolean result =
//        students.stream()
//                .anyMatch(student ->
//                        student.score > 90);结果：true
//因为：David 92.5,Alice 95.5都满足。所以：anyMatch(...)意思：有没有至少一个元素满足条件？只要有一个：true
//allMatch()：是不是全部满足？(用法同上)
//noneMatch()：是不是一个都没有？
//---------------------------------------
//findFirst()：找到第一个
//比如：students.stream()
//        .filter(student ->
//                student.score >= 90)
//        .findFirst();
//意思：先找到 >=90 的学生，David Alice->取第一个,所以得到：David
//不过这里有个新东西：findFirst(),不会直接返回：Student
//而是：Optional<Student>
//为什么是 Optional？
//假设你找：student.score >= 200显然：一个都没有,那 findFirst() 返回什么？如果直接返回：Student
//就很可能：null,Java 为了更明确地处理“可能找到，也可能没找到”，使用：Optional<Student>
//现在不用深入学 Optional。今天只认识最基本写法：
//Optional<Student> result =
//        students.stream()
//                .filter(student ->
//                        student.score >= 90)
//                .findFirst();
//然后：
//if (result.isPresent()) {
//    Student student = result.get();
//
//    System.out.println(student.name);
//}
//Optional 先这样理解,可以暂时把：Optional<Student>理解成：一个盒子，里面“可能有 Student，也可能没有”。
//所以：result.isPresent()就是问：里面有没有东西？而：result.get()表示：把里面那个 Student 拿出来。
//注意：如果里面没有东西，不要直接 get()。否则会报异常。
//----------------------------------------
//max()：找最大值
//Optional<Student> result =
//        students.stream()
//                .max((s1, s2) ->
//                        Double.compare(
//                                s1.score,
//                                s2.score
//                        ));
//它不是只返回最高成绩：95.5而是直接返回：最高分对应的 Student
//如果：result.isPresent()就可以：
//Student best = result.get();
//System.out.println(best.name);
//System.out.println(best.score);
//min() 正好相反
//Optional<Student> result =
//        students.stream()
//                .min((s1, s2) ->
//                        Double.compare(
//                                s1.score,
//                                s2.score
//                        ));
//它们都需要：Comparator来告诉 Java：根据什么比较 Student。
//---------------------------------------
//sorted() 和 max() 的区别
//假设你只是想：找最高分学生。不必：sorted(...).limit(1)
//直接：max(...)更加明确。
//而如果你想：按照成绩把所有学生重新排列。那才使用：sorted(...)
//所以：要整个排序结果→ sorted 只找最大一个→ max
//---------------------------------------
//一个非常实用的组合,需求：找所有及格学生，按照成绩从高到低，只显示前两名姓名。可以：
//students.stream()
//        .filter(student ->
//                student.score >= 60)
//
//        .sorted((s1, s2) ->
//                Double.compare(
//                        s2.score,
//                        s1.score
//                ))
//
//        .limit(2)
//
//        .map(student ->
//                student.name)
//
//        .forEach(name ->
//                System.out.println(name));
//现在 Stream 的意义开始体现出来了：学生集合->筛选及格->成绩降序->取前2->Student转姓名->打印
//读代码的时候就是在读需求。
public class Main {
    public static void main(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("David", 20, 92.5));
        students.add(new Student("Jack", 21, 58.0));
        students.add(new Student("Alice", 19, 95.5));
        students.add(new Student("Tom", 22, 61.0));
        students.stream()
                .sorted((s1,s2)->
                          Double.compare(s2.score,s1.score))
                .limit(2)
                .map(student -> student.name)
                .forEach(student -> System.out.println(student));
        boolean result=students.stream()
                .anyMatch(student -> student.score<60);
        boolean result2=students.stream()
                .allMatch(student -> student.age>=18);
        boolean result3=students.stream()
                .noneMatch(student -> student.score<0);
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
        Optional<Student> result4=
                students.stream()
                        .max((s1,s2)->Double.compare(s1.score,s2.score));
        if(result4.isPresent()){
            Student best=result4.get();
            System.out.println("Best:"+best.name+" Score:"+best.score);
        }
        students.stream()
                .filter(student -> student.score>=60)
                .sorted((s1,s2)->Double.compare(s2.score,s1.score))
                .limit(2)
                .map(student->student.name)
                .forEach(student->System.out.println(student));


    }
    //Stream进阶：
    //
    //sorted()
    //→ 排序
    //
    //limit(n)
    //→ 只取前n个
    //
    //skip(n)
    //→ 跳过前n个
    //
    //anyMatch()
    //→ 至少一个符合？
    //
    //allMatch()
    //→ 全部符合？
    //
    //noneMatch()
    //→ 一个都不符合？
    //
    //findFirst()
    //→ 找第一个
    //
    //max()/min()
    //→ 最大/最小
    //
    //findFirst/max/min
    //可能没有结果
    //→ 返回 Optional
}
