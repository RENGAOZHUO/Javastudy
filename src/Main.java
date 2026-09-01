//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int get = 0;
        int noget = 0;
        double average = 0;
        double total = 0;
        double maxx ;
        double min;
        System.out.println("Please enter student number:");
        int num = scanner.nextInt();
        double[] arr = new double[num];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextDouble();
        }
        maxx = arr[0];
        min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            // 求最大值
            if (arr[i] > maxx) {
                maxx = arr[i];
            }
            // 求最小值
            if (arr[i] < min) {
                min = arr[i];
            }}
            // 累加总分
            for (int i = 0; i < arr.length; i++) {
                total += arr[i];
            }
            average = total / num;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > 70) {
                    get++;
                } else {
                    noget++;
                }
            }
            System.out.println("===== 成绩统计 ======");
            System.out.println("最高分" + maxx);
            System.out.println("最低分" + min);
            System.out.println("平均分" + average);
            System.out.println("及格人数" + get);
            System.out.println("不及格人数" + noget);

        }
    }
