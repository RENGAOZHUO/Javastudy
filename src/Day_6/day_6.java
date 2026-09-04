package Day_6;
//super、构造方法、抽象类
//super.name,super.xxx(),!!!super(name)可以调用父类构造函数且顺序是先构造父类后子类，若父类有构造函数则子类必须用super（）
//抽象类有抽象函数，抽象函数没有函数方法，子类必须重构抽象函数，抽象类不能直接创建对象，like：Animal animal1=new Animal().

public class day_6 {
    public static void main(String[] args){
        Animal dog=new Dog("小黑",3);
        Animal cat=new Cat("小花");

        dog.makeSound();
        dog.eat();

        cat.makeSound();
        cat.eat();

    }
}
