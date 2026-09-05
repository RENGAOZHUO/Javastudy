package Day_7;

public class Duck extends Animal implements Swim,Fly{
    public Duck(String name){
        super(name);
    }
    @Override
    public void eat(){
        System.out.println("鸭子吃草");
    }
    @Override
    public void swim(){
        System.out.println("鸭子游泳");
    }
    @Override
    public void fly(){
        System.out.println("鸭子在飞");
    }
}
