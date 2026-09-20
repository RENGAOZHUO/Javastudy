package Day_33;

import java.sql.*;

//JDBC 增删改查,今天重点掌握 executeUpdate()、参数绑定、增删改查，以及 JDBC 资源管理。
//两个执行方法：executeQuery() 和 executeUpdate()
//方法                    用途                          返回值
//executeQuery()         执行 SELECT 查询               ResultSet
//executeUpdate()        执行 INSERT、UPDATE、DELETE    int，受影响行数
//例如：ResultSet rs = ps.executeQuery();返回一张供 Java 读取的结果集。
//而：int rows = ps.executeUpdate();返回受到这次操作影响的记录数量。
//假设成功删除一条学生记录：rows = 1
//如果没有找到需要删除的学生：rows = 0 你可以把 rows 理解为：这条 SQL 影响了多少行数据。
//INSERT：用 Java 添加学生,我们的第一个目标是：向 MySQL 添加一名新学生：Bob，20 岁，成绩 86.5。
//先准备带问号的 SQL String sql ="INSERT INTO student (name, age, score) VALUES (?, ?, ?)";
//为什么有三个问号？因为我们需要设置三个值：第1个 ? → name 第2个 ? → age 第3个 ? → score
//对应：ps.setString(1, "Bob"); ps.setInt(2, 20); ps.setDouble(3, 86.5);
//这里的 1、2、3 是 SQL 中占位符的位置，从 1 开始，不是从 0 开始。
//注意今天的关键变化：int rows = ps.executeUpdate();
//这次不使用：ps.executeQuery();因为 INSERT 不会产生供我们逐行读取的查询结果。
//注意：每运行一次上面的 INSERT 程序，就会再插入一条 Bob。 所以不要反复运行添加程序，否则表里可能出现多个 Bob。
//------------------------------------
//UPDATE：修改已有学生的成绩
//接下来我们希望：把 Bob 的成绩从 86.5 修改成 90.0。
//SQL 是：UPDATE student SET score =90.0 WHERE name = 'Bob';
//但这里我希望你养成一个好习惯：修改单个学生时，优先根据唯一的 id 定位，而不是根据姓名。
//因为两个学生完全可能都叫 Bob。假设你刚才插入的 Bob 的 id 是 5，那么我们可以：
//UPDATE student SET score =90.0 WHERE id = 5;
//Java 中：String sql ="UPDATE student SET score = ? WHERE id = ?";
//需要设置两个参数：ps.setDouble(1, 90.0);ps.setInt(2, 5);
//然后：int rows = ps.executeUpdate();
//--------------------------------------
//DELETE：删除学生
//假设我们希望删除刚才添加的 Bob。
//SQL：DELETE FROM student WHERE id = 5;
//Java 中：String sql ="DELETE FROM student WHERE id = ?";
//绑定：ps.setInt(1, 5);然后：int rows = ps.executeUpdate();
//--------------------------------------
//CRUD 总结，CRUD 是后端开发中非常常见的缩写：
//字母        英文           SQL                    JDBC
//C          Creat         INSERT                  executeUpdate()
//R          Read          SELECT                  executeQuery()
//U          Update        UPDATE                  executeUpdate()
//D          Delete        DELETE                  executeUpdate()
//你以后做学生管理系统、用户系统、订单系统，几乎都离不开这些操作。
public class Main {
    public static void main(){
        String url = "jdbc:mysql://localhost:3306/java_study";
        String user="root";
        String password = System.getenv("DB_PASSWORD");
        String sql = "SELECT *FROM student";
        try(
                Connection conn= DriverManager.getConnection(url,user,password);
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ){
                  while(rs.next()){
                      System.out.println(rs.getInt("id")+" "
                      +rs.getString("name")+" "
                      +rs.getInt("age")+" "
                      +rs.getDouble("score"));
                  }


            }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
