package Day_21;
//今天解决的问题是：如果程序在执行过程中突然发生异常，还能保证文件流被关闭吗？这也是你从“会写练习代码”走向“比较规范的 Java 代码”的一步。
//可能出现：打开文件->发生异常->直接进入 catch->close() 被跳过
//以前可以用 finally 解决,它的特点是：不管有没有发生异常，通常都会执行。
//但这样代码比较麻烦。所以 Java 后来提供了一个非常重要的语法：
//try-with-resources
//try (...) {...}把需要关闭的资源放进 try() 里面。Java 就会：在使用完毕后自动关闭。
//所以你不用自己：fis.close();
//遇到不同需求，该怎么选择？比如：
//我要复制图片,优先BufferedInputStream BufferedOutputStream因为图片是二进制文件。
//我要逐行读取 txt优先：BufferedReader因为：readLine()很方便。
//我要写普通文本优先：BufferedWriter
//我要保存：int double boolean保持它们的数据类型：DataOutputStream
//我要保存：StudentArrayList<Student>优先：ObjectOutputStream


import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        /*ArrayList<Student> students=new ArrayList<>();//这个集合里的每一个元素，都必须是一个 Student 对象。
        students.add(new Student("David",20,92.5));
        students.add(new Student("Jack",21,88.0));
        students.add(new Student("Alice",19,95.5));//往ArrayList里面填值需要填对应类型的对象，所以填类时不能直接将类值一个一个用add填入，而是要填入已近初始化的student值
        try(FileOutputStream fos=new FileOutputStream("src/Day_21/students.dat");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            FileInputStream fis=new FileInputStream("src/Day_21/students.dat");
            ObjectInputStream ois=new ObjectInputStream(fis);){

            oos.writeObject(students);


            ArrayList<Student> x=(ArrayList<Student>) ois.readObject();
            for(Student student:x){
                System.out.println("姓名"+student.name);
                System.out.println("年龄"+student.age);
                System.out.println("成绩"+student.score);
            }

        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }*/
        ArrayList<Student> student=new ArrayList<>();
        student.add(new Student("David",20,92.5));
        student.add(new Student("Jack",21,88.0));
        student.add(new Student("Alice",19,95.5));
        student.add(new Student("Tom",22,61.0));
        try(
                FileOutputStream fos=new FileOutputStream("src/Day_21/student");
                ObjectOutputStream oos=new ObjectOutputStream(fos);
                FileInputStream fis=new FileInputStream("src/Day_21/student");
                ObjectInputStream ois=new ObjectInputStream(fis);
        ){
            oos.writeObject(student);
            ArrayList<Student> loadedStudents=(ArrayList<Student>) ois.readObject();
            for(Student loadedStudent:loadedStudents){
                System.out.println("姓名："+loadedStudent.name);
                System.out.println("年龄"+loadedStudent.age);
                System.out.println("成绩"+loadedStudent.score);
            }
            double max=loadedStudents.get(0).score;//用get来获取ArrayList里存的值，复习内容！！！
            for(Student loadedStudent:loadedStudents){
                if(loadedStudent.score>max){
                    max= loadedStudent.score;
                }
            }
            System.out.println("最高分:"+max);
            double sum=0;
            for(Student loadedStudent:loadedStudents){
                sum+=loadedStudent.score;
            }
            double average=sum/loadedStudents.size();//获取集合长度，也是复习！！！！
            System.out.println("平均分"+average);
            boolean found=false;//设置查找flag来标记有没有找到对应值；
            for(int i=0;i<loadedStudents.size();i++){

                if(loadedStudents.get(i).name.equals("Alice")){//数值比较用 ==，字符串内容比较用 equals()；
                    found=true;
                    System.out.println("姓名："+loadedStudents.get(i).name);
                    System.out.println("年龄："+loadedStudents.get(i).age);
                    System.out.println("分数"+loadedStudents.get(i).score);
                    break;
                }
            }
            if(!found){
                System.out.println("未查找到该同学！");
            }

        }catch(IOException|ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
//Day21 try-with-resources
//
//try (
//    创建需要关闭的资源
//) {
//
//    使用资源
//
//}
//
//→ Java自动关闭
//
//
//IO选择：
//
//二进制文件
//→ BufferedInputStream / BufferedOutputStream
//
//文本
//→ BufferedReader / BufferedWriter
//
//基本数据类型
//→ DataInputStream / DataOutputStream
//
//对象
//→ ObjectInputStream / ObjectOutputStream
