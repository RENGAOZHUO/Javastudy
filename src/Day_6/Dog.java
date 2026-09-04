package Day_6;

public class Dog extends Animal {
    int age;
    public Dog(String name,int age){
        super(name);
        this.age=age;
        System.out.println("Dog构造方法");
    }

    @Override
    public void makeSound(){
        System.out.println(name+"正在汪汪叫");
    }

}
