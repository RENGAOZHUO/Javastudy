package Day_8;

//java集合--ArrayList（动态分布内存的数组）
//需要“头文件”import java.util.ArrayList;
//使用方法：ArrayList<String> names = new ArrayList<>();
//增：.add(),获取：.get()下标依旧从0开始,删：remove(),改：set(),获取长度：size（）注意：java里获取数组长度的是length
//java里诸如length的用法是System.out.println(name.length);
/*集合的泛型可以使用String，但不能直接使用基本的数据类型诸如int，double...
所以要使用对应的包装类：Integer
                   Double
                   Character
                   Boolean
 */
//进阶循环：for(String name:names){System.ou.println(name);}

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("苹果");
        fruits.add("香蕉");
        fruits.add("橘子");
        fruits.add("西瓜");
        System.out.println(fruits.get(0));
        System.out.println(fruits.size());
        fruits.set(1,"葡萄");
        fruits.remove(0);
        for(int i=0;i<fruits.size();i++){
            System.out.println(fruits.get(i));
        }
        for(String fruit:fruits){
            System.out.println(fruit);
        }
        System.out.println(fruits);
        ArrayList<Integer> numbers =new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);
        int sum=0;
        for(Integer number:numbers){

            sum+=number;
        }
        System.out.println(sum);
        ArrayList<Integer> finds = new ArrayList<>();
        finds.add(12);
        finds.add(5);
        finds.add(28);
        finds.add(3);
        finds.add(19);
        finds.add(35);
        finds.add(8);
        int max=finds.get(0);
        for(Integer find:finds){
            if(max>find){
                continue;
            } else if (max<find) {
                max=find;
            }
        }
        System.out.println(max);
    }
}
