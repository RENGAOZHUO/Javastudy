package Day_31;

public class Student {
    String name;
    int age;
    double score;
    public  Student(String name,int age,double score){
        this.name=name;
        this.age=age;
        this.score=score;
    }
    public static String getName(Student student){
        return student.name;
    }
}
