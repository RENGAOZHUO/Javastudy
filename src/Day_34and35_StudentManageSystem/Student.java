package Day_34and35_StudentManageSystem;
//我们重写了 toString()。还记得 Day31 吗？之前你直接打印 Student，得到的是：
//Day_31.Student@4f023edb
//今天重写后，就能直接使用：System.out.println(student);来输出学生信息。
public class Student {
    private int id;
    private String name;
    private int age;
    private double score;

    public Student(int id,String name,int age,double score){
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public double getScore(){
        return score;
    }

    @Override
    public String toString(){
        return "ID:"+id+",姓名:"+name+",年龄:"+age+",成绩:"+score;
    }
}
