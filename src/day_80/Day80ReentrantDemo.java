package day_80;

public class Day80ReentrantDemo {
    public synchronized void methodA() {

        System.out.println("进入 A");

        methodB();

        System.out.println("退出 A");
    }

    public synchronized void methodB() {

        System.out.println("进入 B");

        methodC();
    }

    public synchronized void methodC() {

        System.out.println("进入 C");
    }

    public static void main(String[] args) {

        Day80ReentrantDemo demo =
                new Day80ReentrantDemo();

        demo.methodA();
    }
}
