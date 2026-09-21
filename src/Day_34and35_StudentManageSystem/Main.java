package Day_34and35_StudentManageSystem;

import java.sql.SQLException;
import java.util.Scanner;

//第一个 Java + MySQL 综合项目
//学生管理系统 V1.0 Java + JDBC + MySQL + 面向对象
//今天先实现查询所有学生、根据 ID 查询、添加学生三个功能，并建立清晰的项目结构。明天继续完善修改、删除和菜单交互。
//今天最重要的新思想：将代码分层,从今天开始我们把程序分为三个主要部分。
//Main.java与用户交互，处理菜单和输入->StudentDao.java负责执行 SQL，操作数据库->MySQL保存 student 表中的数据
//另外，我们还需要一个 Student.java，负责表示学生对象。
//这里出现的新单词是 DAO，即 Data Access Object，数据访问对象。它的职责是专门处理数据的存取
//Main程序入口、菜单和用户输入,Student表示一名学生,DBUtil获取 MySQL 数据库连接,StudentDao执行学生相关的 SQL
//
public class Main {
    public static void main(String[] args){
        StudentDao dao = new StudentDao();

        try(Scanner sc = new Scanner(System.in)){
            while(true){

                System.out.println("\n====学生管理系统=====");
                System.out.println("1.查询所有学生");
                System.out.println("2.根据ID查询学生");
                System.out.println("3.添加学生");
                System.out.println("4.修改学生成绩");
                System.out.println("5.删除学生");
                System.out.println("0.推出系统");
                System.out.println("请输入你的选择：");

                String choice = sc.nextLine();

                try{
                    switch (choice){
                        case "1":{
                            for(Student s:dao.findall()){
                                System.out.println(s);
                            }
                            break;
                        }
                        case "2":{
                            System.out.println("请输入学生ID：");

                            int id =Integer.parseInt(sc.nextLine());

                            Student student = dao.findById(id);
                        //String input = sc.nextLine();第一步：读取用户输入,int id = Integer.parseInt(input);第二步：转换成整数
                            if(student!=null){
                                System.out.println(student);
                            }else{
                                System.out.println("学生不存在！");
                            }
                            break;
                        }
                        case "3":{
                            System.out.println("请输入姓名、年龄、成绩，用空格分隔：");

                            String input = sc.nextLine();

                            String[] parts = input.trim().split("\\s+");
                            //这行代码做了两件事：
                            //trim()：删除字符串开头和结尾的空白。
                            //split("\\s+")：按照一个或多个空白字符拆分字符串。
                            //注意，三个元素仍然都是字符串，所以后两个需要转换为数字。这个简单版本暂时要求姓名中不含空格。
                            if (parts.length != 3) {
                                System.out.println("输入格式错误！例如：Alice 20 95.5");
                                break;
                            }

                            String name = parts[0];
                            int age = Integer.parseInt(parts[1]);
                            double score = Double.parseDouble(parts[2]);

                            if (age <= 0 || !Double.isFinite(score)
                                    || score < 0 || score > 100) {
                                System.out.println("年龄或成绩不合法！");
                                break;
                            }

                            int row = dao.addStudent(name,age,score);

                            if(row>0){
                                System.out.println("添加学生成功！");
                            }else{
                                System.out.println("添加学生失败！");
                            }
                            break;
                        }
                        case "4":{
                            System.out.println("请输入学生 ID 和新成绩，用空格分隔：");

                            String[] parts = sc.nextLine().trim().split("\\s+");

                            if (parts.length != 2) {
                                System.out.println("输入格式错误！例如：3 95.5");
                                break;
                            }

                            int id = Integer.parseInt(parts[0]);
                            double score = Double.parseDouble(parts[1]);

                            if (!Double.isFinite(score) || score < 0 || score > 100) {
                                System.out.println("成绩不合法！");
                                break;
                            }

                            int row = dao.updateSCore(id,score);

                            if(row>0){
                                System.out.println("修改成绩成功！");
                            }else{
                                System.out.println("修改失败：学生不存在!");
                            }
                            break;
                        }
                        case "5":{
                            System.out.println("请输入要删除的学生ID:");

                            int id = Integer.parseInt(sc.nextLine());

                            int row = dao.deleteById(id);

                            if(row>0){
                                System.out.println("添加学生成功！");
                            }else{
                                System.out.println("添加学生失败！");
                            }
                            break;
                        }
                        case "0":{
                            System.out.println("已退出系统");
                            return;//return：结束整个 main 方法
                        }
                        default:{
                            System.out.println("输入格式错误，请输入数字");
                        }
                    }
                }catch(SQLException e){
                    System.out.println("数据库操作失败：" + e.getMessage());
                }catch(NumberFormatException e){//NumberFormatException 的意思是：数字格式异常。它主要发生在程序尝试把一个不符合格式的字符串转换为数字时。
                                //例如：int id = Integer.parseInt("abc");这里 "abc" 并不是合法的整数，所以转换失败，Java 就会抛出 NumberFormatException。
                    System.out.println("数字格式错误，请重新输入！");
                }
            }
        }
    }
}
