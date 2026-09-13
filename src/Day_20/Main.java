package Day_20;
//进入对象序列化：ObjectOutputStream / ObjectInputStream + Serializable,把整个 Student 对象直接写进去Student → writeObject()
//序列化 Serialization
//假设内存中有：Student s = new Student("David", 20, 92.5);这个对象存在于 Java 程序的内存中。
//程序一关闭：Student对象就消失
//如果我们希望把它保存下来，就需要：Java对象->转换成可以保存的形式->文件
//这个过程就叫Serialization序列化
//反过来就叫Deserialization反序列化
//第一个关键类：ObjectOutputStream，依旧先创建字节流
//oos.writeObject(student);直接把整个对象交给它。
//但是 Student 类不能直接写(class student{...}),直接写会报错，需要：implements Serializable
//public class Student implements Serializable
//Serializable没有方法，它属于一种：标记接口（Marker Interface）
//意思可以理解成：这个类通过 implements Serializable 给 Java 做了一个标记：“我允许被序列化。”
//所以class Student implements Serializable不是为了让你重写某个方法。而是为了告诉 Java：Student对象可以进行序列化
//怎么读取？使用ObjectInputStream
//Student student = (Student) ois.readObject();
//ois.readObject()返回的是：Object，Java 不确定里面究竟是什么。
//(Student)相当于告诉 Java：把这个 Object 当成 Student 使用。这就是你以前接触过的强制类型转换思想。
//读取的时候会多一个异常：ClassNotFoundException因为 Java 读取文件里的对象时，需要找到对应的：Student类
//所以现在的catch:catch (IOException | ClassNotFoundException e)
//读取：怎么知道文件里有几个对象？-->直接保存 ArrayList
//ArrayList<Student> students = new ArrayList<>();students.add( new Student("David", 20, 92.5))；...
//然后：oos.writeObject(students);整个集合直接保存。
//读取：ArrayList<Student> students = (ArrayList<Student>) ois.readObject();
//然后：for (Student student : students) { ...}
//预习:以后很容易看到：private static final long serialVersionUID = 1L;(这句话放在声明类里）
//Student 类的“序列化版本编号”。因为你把对象写进文件之后，将来可能修改：Student类，Java 需要判断：文件里的旧 Student 和现在这个 Student 是不是兼容？
//serialVersionUID 就参与这个判断。
//—————一个疑问解答——————：写入时：Student s1 = new Student("David", 20, 92.5);这里的 s1 只是写入那一刻的变量名。
//文件里并不会保存：变量名叫 s1。所以程序重新运行以后，原来的：s1早就不存在了。读取时其实是在创建一个新的变量来接收恢复出来的对象：
//但是：如果在同已作用域下使用两次Student student仍然会报错

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static  void main(String[] args){
        /*Student student=new Student("David",20,92.5);
        try{
            FileOutputStream fos=new FileOutputStream("src/Day_20/student.dat");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(student);
            oos.close();
        }catch(IOException e){
            e.printStackTrace();
        }*/
        /*try{
            FileInputStream fis=new FileInputStream("src/Day_20/student.dat");
            ObjectInputStream ois=new ObjectInputStream(fis);
            Student student=(Student) ois.readObject();
            System.out.println("姓名："+student.name);
            System.out.println("年龄："+student.age);
            System.out.println("成绩"+student.score);

        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }*/
        ArrayList<Student> students=new ArrayList<>();//这个集合里的每一个元素，都必须是一个 Student 对象。
        students.add(new Student("David",20,92.5));
        students.add(new Student("Jack",21,88.0));
        students.add(new Student("Alice",19,95.5));//往ArrayList里面填值需要填对应类型的对象，所以填类时不能直接将类值一个一个用add填入，而是要填入已近初始化的student值
        try{
            FileOutputStream fos=new FileOutputStream("src/Day_20/students.dat");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(students);
            oos.close();
            FileInputStream fis=new FileInputStream("src/Day_20/students.dat");
            ObjectInputStream ois=new ObjectInputStream(fis);
            ArrayList<Student> x=(ArrayList<Student>) ois.readObject();
            for(Student student:x){
                System.out.println("姓名"+student.name);
                System.out.println("年龄"+student.age);
                System.out.println("成绩"+student.score);
            }
            oos.close();
            ois.close();
        }catch(IOException|ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
//Day20 对象序列化
//
//Serializable
//→ 表示这个类允许被序列化
//
//ObjectOutputStream
//→ writeObject()
//
//ObjectInputStream
//→ readObject()
//
//序列化：
//对象 → 文件
//
//反序列化：
//文件 → 对象
//
//readObject() 返回 Object
//所以通常需要强制类型转换
//
//对象文件不是普通文本文件
//打开看到乱码正常