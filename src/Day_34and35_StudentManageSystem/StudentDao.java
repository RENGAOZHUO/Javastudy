package Day_34and35_StudentManageSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//这是今天最重要的部分。
//我们先只实现一个方法：public List<Student> findAll()
//目标是：从数据库查询所有学生，并返回一个装有 Student 对象的集合。
//之前已经分别学过：ResultSet 以及：ArrayList<Student>今天要把它们组合起来。
//先理解返回值，方法声明：public List<Student> findAll()
//这里的：List<Student>表示这个方法返回一个学生集合。
//在方法内部，我们可以创建：List<Student> students = new ArrayList<>();
//再从数据库逐行读取：while (rs.next()) {}
//每读取到一行，就构建一个 Student 对象，再加入集合：students.add(student);
//最后：return students;
public class StudentDao {
    public List<Student> findall()throws SQLException{
        List<Student> students = new ArrayList<>();

        String sql = "SELECT id,name,age,score FROM student ORDER BY id";

        try(
                Connection conn = DBUtil.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery()
                ){
            while(rs.next()){
                int id =rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double score = rs.getDouble("score");

                Student student =
                        new Student(id,name,age,score);

                students.add(student);
            }
        }
    return students;
    }
    public Student findById(int id) throws SQLException{
        String sql =
                "SELECT id,name,age,name,score FROM student WHERE id = ?";

        try(
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ){
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    int findId = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    double score = rs.getDouble("score");
                    Student student = new Student(findId,name,age,score);
                    return student;
                }

            }
        }
        return null;//这里的：return null;表示数据库里没有对应的学生。
    }
    public int addStudent(String name,int age,double score)throws SQLException{
        String sql =
                "INSERT INTO student(name,age,score) VALUES (?,?,?)";
        try(
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
                ){
            ps.setString(1,name);
            ps.setInt(2,age);
            ps.setDouble(3,score);
            return ps.executeUpdate();
        }

    }
    public int updateSCore(int id,double newScore) throws SQLException{

        String sql = "UPDATE student SET score=? WHERE id =?";

        try(Connection conn = DBUtil.getConnection();
        PreparedStatement ps =
                conn.prepareStatement(sql)){
            ps.setDouble(1,newScore);//为什么先 setDouble，再 setInt？因为占位符的编号取决于 SQL 中 ? 出现的顺序，而不是 Java 方法参数的顺序。
            ps.setInt(2,id);

            return ps.executeUpdate();//为什么使用 executeUpdate()？它用于执行 INSERT、UPDATE、DELETE 等语句，并返回受影响的行数。int rows = dao.updateScore(3, 90);
        }//为什么这里返回 int，而不是 void？因为我们希望调用这个方法的人知道操作结果。
    }
    public int deleteById(int id) throws SQLException{
        String sql = "DELETE FROM student WHERE id=?";

        try(
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                )
        {
            ps.setInt(1,id);
            return ps.executeUpdate();
        }
    }

}
//你可以把执行过程理解成：数据库 student 表->rs.next()->读取当前行->new Student(...)->students.add(student)->继续下一行->返回 List<Student>

