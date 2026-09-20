package Day_32;

import java.sql.*;

//Java 连接 MySQL（JDBC）
//今天的目标让 Java 程序真正连接到本地 MySQL，执行 SQL，并读取数据库中的学生信息。
//Java 程序学生查询 / 添加学生 / 修改成绩--通过 JDBC 发送 SQL->MySQL 数据库,student 数据表<-返回查询结果--Java 接收数据ResultSet → Student 对象
//JDBC 的全称是 Java Database Connectivity，可以简单理解为：
//Java 程序访问关系型数据库的一套标准 API。你写 Java，数据库负责存储数据，JDBC 负责让双方能够交流。
//---------------------------------
//Java 怎么知道如何连接 MySQL？
//你前面已经学过：FileInputStream ObjectInputStream BufferedReader这些属于 Java 自己提供的功能。
//但是 MySQL 是另一个独立的软件。因此，我们需要额外引入 MySQL 提供的 JDBC 驱动：MySQL Connector/J。
//可以把驱动简单理解成：一个让 Java 的 JDBC 接口能够与 MySQL 数据库通信的组件。
//在你目前的 IntelliJ IDEA 项目里添加驱动
//1.从 MySQL 官方 Connector/J 下载页面  下载适用于 MySQL 的 JDBC 驱动。选择平台无关的安装包，解压后找到其中类似 mysql-connector-j-版本号.jar 的文件。
//2.打开 IDEA，在菜单中找到：文件 → 项目结构（Project Structure）。
//3.找到模块（Modules）→ 依赖（Dependencies），通过 + 将下载的 JAR 文件添加到项目模块依赖中。
//4.点击应用、确定。接下来创建今天的 Day_32 软件包。
//注意，后面我们会正式学习 Maven，它可以自动管理这些依赖。今天先手动引入驱动，是为了让你看清楚 JDBC 的整个工作过程，不要求你现在就掌握 Maven。
//----------------------------------
//第一次连接数据库：Connection
//今天先认识：Connection它表示一个数据库连接。
//创建数据库连接时，需要知道三个信息：数据库地址，数据库用户名，数据库密码
//例如你的 MySQL 在本机：localhost，端口是：3306，数据库叫：Java_study
//那么 JDBC URL 就是：String url = "jdbc:mysql://localhost:3306/java_study";
//拆开：
//jdbc:mysql://localhost:3306/java_study
//     │            │      │       │
//     │            │      │       └─ 数据库名称
//     │            │      └───────── 端口号
//     │            └──────────────── 本机地址
//     └───────────────────────────── MySQL协议
//接着：Connection conn =
//        DriverManager.getConnection(url, user, password);
//就可以尝试建立数据库连接。
//这里的：DriverManager就是 Java JDBC 中负责管理驱动并帮助获取连接的工具类。
//现代 MySQL Connector/J 正常配置后通常会自动注册驱动，不需要特意调用 Class.forName()。
//-------------------------------
//连接成功以后，怎么执行 SQL？
//之前学过：SELECT * FROM student;现在，我们希望直接在 Java 里执行这条 SQL。
//今天介绍：PreparedStatement可以把它理解成：一个准备好执行 SQL 的对象。
//例如：String sql = "SELECT * FROM student";
//然后：PreparedStatement ps =
//        conn.prepareStatement(sql);
//这时候 Java 已经准备好了查询语句。接下来：
//ResultSet rs = ps.executeQuery();就真正执行了查询。
//注意这三个对象的关系：Connection数据库连接->PreparedStatement准备SQL->executeQuery()执行查询->ResultSet接收查询结果
//ResultSet 是什么？
//你执行 SQL 后，会得到一个结果表：
//但 JDBC 不会自动把它转换成 ArrayList<Student>。它首先提供一个：ResultSet你可以把它想成一个用于读取查询结果的对象。
//最常用的方法是：rs.next()它会尝试移动到下一行；成功返回 true，没有下一行返回 false。
//所以你可以写：while (rs.next()) {
//
//    String name = rs.getString("name");
//
//    int age = rs.getInt("age");
//
//    double score = rs.getDouble("score");
//
//    System.out.println(name + " " + age + " " + score);
//}
//rs.getString("name")表示：取得当前行 name 这一列的值。
//同理：rs.getInt("age")rs.getDouble("score")就是取得年龄和成绩。
//---------------------------------------
//再认识 PreparedStatement 的一个重要功能：参数化 SQL
//假如我们只想查找：成绩 >= 60 的学生;SELECT * FROM student WHERE score >= 60;
//但是如果这个 60 来自 Java 变量呢？例如：double minScore = 60;
//我们可以使用占位符：String sql ="SELECT * FROM student WHERE score >= ?";
//其中：?表示：这里的值等会儿再设置。
//然后：PreparedStatement ps =conn.prepareStatement(sql);
//ps.setDouble(1, minScore);注意：setDouble(1, minScore)里的 1 表示第一个问号，不是数据库里的第一列。
//最终再：ResultSet rs = ps.executeQuery();就能取得符合条件的学生。
//以后你做用户登录、查询订单、按学号查询学生时，会频繁使用这种写法。它还能够避免直接拼接用户输入导致的 SQL 注入问题。
//
public class Main {
    public static void main(){
     String url="jdbc:mysql://localhost:3306/java_study";
     String user="root";
        String sql="SELECT *FROM student WHERE score>=?";
        double minScore=60;
     String password=System.getenv("DB_PASSWORD");//使用运行配置的密码记得调用System.getenv()方法
     try(Connection conn=DriverManager.getConnection(url,user,password);
         PreparedStatement ps=conn.prepareStatement(sql)    ;

     ){
         ps.setDouble(1,minScore);//必须先于ResultSet执行才有用，至于之后的ResultSet可以再写一个try
         ResultSet rs= ps.executeQuery();//ResultSet rs = ps.executeQuery() 确实可以写在 try() 里面。 我刚才为了让你看清 setDouble() 必须先于 executeQuery() 执行，把 rs 放进了 {}
         while(rs.next()){
             String name=rs.getString("name");
             int age=rs.getInt("age");
             double score=rs.getDouble("score");
             System.out.println(name + " " + age + " " + score);
         }

     }catch(SQLException e){
         e.printStackTrace();
     }
    }
}
