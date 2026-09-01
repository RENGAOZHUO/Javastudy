//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
       Scanner scanner = new Scanner(System.in);
       System.out.println("请输入姓名：");
       String name =scanner.nextLine();
       System.out.println("Java成绩：");
       double jascore = scanner.nextDouble();
       System.out.println("数学成绩：");
       double mathscore = scanner.nextDouble();
       System.out.println("英语成绩成绩：");
       double englishscore = scanner.nextDouble();
       System.out.println("name:"+name);
       System.out.println("Java:"+ jascore);
       System.out.println("math:"+mathscore);
       System.out.println("englsih:"+englishscore);
        double average=(jascore+mathscore+englishscore)/3;
       System.out.println("平均分"+average);
       if(average>=90){
           System.out.println("成绩优秀");
       }else if(average>=80){
           System.out.println("良好");
       }else if(average>=60){
           System.out.println("及格");
       }else{
           System.out.println("不合格");
       }

    }
}