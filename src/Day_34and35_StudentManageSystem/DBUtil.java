package Day_34and35_StudentManageSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Day32、Day33 你已经反复写过：DriverManager.getConnection(url, user, password);
//如果每个功能都重复定义 URL 和账号，代码会很乱。所以今天我们创建一个专门获取数据库连接的工具类。
//这里出现了：public static Connection getConnection()
//你可以把它理解成：我专门设计了一个方法，用来返回数据库连接。
//以后想连接数据库时，直接写：Connection conn = DBUtil.getConnection();
//就不用每次把 URL、用户名和密码重新写一遍了。
//注意，DBUtil 目前只是练习用的连接工具，并不是数据库连接池。后续项目阶段我们会正式学习 HikariCP 等连接池技术。
public class DBUtil {
    private static final String URL =
            "jdbc:mysql://localhost:3306/java_study";

    private static final String USER = "root";

    public static Connection getConnection()throws SQLException{
        String password = System.getenv("DB_PASSWORD");

        if(password == null||password.isEmpty()){
            throw new SQLException("请先配置DB_PASSWORD环境变量");
        }

        return DriverManager.getConnection(URL,USER,password);
    }
}
//1. static：让变量属于类，而不是某个对象
//2. final：让变量不能被重新赋值
//static 用来表示类级别的成员；final 用来限制变量重新赋值。二者不是必须同时出现的。
//在当前代码里，静态方法不能直接访问非静态成员变量；也可以通过对象访问，但那需要改变写法。
//meaning：getConnection() 是 static，URL 一定也必须是 static
