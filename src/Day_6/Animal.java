package Day_6;

public abstract  class Animal {
    String name;

    public Animal(String name){
        this.name=name;
        System.out.println("Animal构造方法");
    }
    public abstract void makeSound();
    public  void eat(){
        System.out.println(name+"正在吃东西");
    }
}
