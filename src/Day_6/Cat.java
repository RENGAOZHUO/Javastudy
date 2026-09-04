package Day_6;

public class Cat extends Animal {
    public Cat(String name){
        super(name);
    }
    @Override
    public void makeSound(){
        System.out.println(name+"正在喵喵叫");
    }
}
