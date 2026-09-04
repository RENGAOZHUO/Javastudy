package Day_5;//继承（extend）和重构（@Override)以及多态：父类类型变量可以指向子类对象，并根据实际对象调用重写的方法
class Animal{
    String name;

    public void makeSound(){
        System.out.println("动物发出声音");
    }
}

class Cat extends Animal{
    @Override
    public void makeSound(){
        System.out.println("喵喵");
    }
}

class Dog extends Animal{
    @Override
    public void makeSound(){
        System.out.println("汪汪");
    }
}

class Person{
    String name;
    int age;
    public void introduce(){
        System.out.println("I am a person");
    }
}

class Student extends Person{
    @Override
    public void introduce(){
        System.out.println("I am a student");
    }
}

class Teacher extends Person{
    @Override
    public void introduce(){
        System.out.println("I am a teacher");
    }
}
public class day_5 {
    public static void main(String[] args){
       Person person1 = new Person();
       Student person2 = new Student();
       Person person3 = new Teacher();
       person1.introduce();;
       person2.introduce();;
       person3.introduce();
    }
}
