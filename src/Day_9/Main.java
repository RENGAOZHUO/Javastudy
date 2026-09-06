package Day_9;
//HashMap:"我想通过一个东西，快速招待另一个东西"
//需要import java.util.HashMap
//创建：HashMap<Integer,String> students = new HashMap<>();
//上面这个例子中，key是Integer，value是String
/*put增添数据：students.put(1001,“张三”)即1001->张三
get：根据key寻找value，例如:System.out.println(students.get(1001)),输出：张三
修改：还是使用put,通过同样的key来修改key指引的value
remove：删除，需要的参数只有key
containsKey：判断有没有某个key，若有返回true，无返回false
containsValue：判断有没有某个value，同上
size（）获取HashMap的大小
 */

import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        HashMap<String,Integer> scores = new HashMap<>();
        scores.put("张三",90);
        scores.put("李四",85);
        scores.put("王五",95);
        scores.put("赵六",78);
        for(String name:scores.keySet()){
            Integer score=scores.get(name);
            System.out.println(name+":"+score);
        }
        scores.get("张三");
        scores.put("李四",88);
        scores.remove("赵六");
        System.out.println(scores.containsKey("王五"));
        for(String name:scores.keySet()){
            Integer score=scores.get(name);
            System.out.println(name+":"+score);
        }
        int count=0;
        for(String name:scores.keySet()){
            count++;
        }
        System.out.println("The count of students is"+count);
        HashMap<String,Double> products = new HashMap<>();
        products.put("苹果",5.5);
        products.put("香蕉",3.2);
        products.put("牛奶",4.5);
        products.put("面包",4.5);
        System.out.println(products.get("牛奶"));
        System.out.println(products.get("可乐"));
        String[] names ={
                "张三",
                "李四",
                "张三",
                "王五",
                "李四",
                "张三",
        };
        HashMap<String,Integer> sums = new HashMap<>();
        for(String name:names){
            if(sums.containsKey(name)){
                int count1=sums.get(name);
                sums.put(name,count1+1);
            }
            else{
                sums.put(name,1);
            }
        }
        System.out.println(sums);
    }
}
