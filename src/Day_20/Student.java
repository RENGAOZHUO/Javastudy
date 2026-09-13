package Day_20;

import java.io.Serializable;

public class Student implements Serializable {
    String name;
    int age;
    double score;
    public Student(String name,int age,double score){
        this.name=name;
        this.age=age;
        this.score=score;
    }
}
