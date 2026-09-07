package Day_10;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*常见异常：ArithmeticException数学运算错误
          ArrayIndexOutOfBoundsException数组下标越界
          NullPointerException对null对象进行操作
          NumberFormatException字符串转换数字失败
          IndexOutOfBoundException集合/字符串下标越界
 */
//try-catch try->执行代码->发现异常->停止执行try中剩余代码->进入catch->处理异常->继续执行后面的代码
//catch(ArithmeticException e),e:Java把这次发生的异常装进一个对象里，然后交给我
//一个try可以有多个catch
//很多具体异常都属于Exception体系，使用catch时可以用catch（Exception e)来获取各类异常
//finally：无论有没有异常都会执行
public class Main {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int dividend;
        int divisor;
       /* do {
            System.out.println("请输入被除数：");
            dividend = scanner.nextInt();
            System.out.println("请输入除数：");
            divisor = scanner.nextInt();
            try{
                System.out.println(dividend/divisor);
            }catch(ArithmeticException e){
                System.out.println("除数不能为0！");
            }
        } while (dividend >= 0);*/
        /*int arr[]={10,20,30,40,50};
        int index;
        do{
            System.out.println("请输入数组下标：");
            index=scanner.nextInt();
            try{
                System.out.println(arr[index]);
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("下标越界！");
            }
        }while(index>=0);*/
        ArrayList<String>name=new ArrayList<>();
        ArrayList<Integer>score=new ArrayList<>();
        name.add("张三");
        score.add(90);
        int arr[]={0};
        int num;
        do{
            System.out.println("请输入学生编号：");
            num=scanner.nextInt();
            try{
                System.out.println(name.get(num)+": "+score.get(num));
            }catch(Exception e){
                System.out.println("学生编号不存在！");
            }
        }while(num>=0);
    }
}
