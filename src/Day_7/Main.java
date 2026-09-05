package Day_7;
// interface接口，接口函数没有函数方法，接口函数默认是public的，其他类要实现接口的函数，使用接口的关键字是implements。
// 一个类可以有多个接口例如class Dog extends Animal implements Swim,Fly,但只能有一个父类继承
// 查漏补缺：@Override后直接跟函数，不能用;

public class Main {
    public static void main(String[] args){
        Dog animal1=new Dog("旺财");
        Duck animal2=new Duck("唐老鸭");
        animal1.eat();
        animal1.swim();
        animal2.eat();
        animal2.swim();
        animal2.fly();

    }
}
