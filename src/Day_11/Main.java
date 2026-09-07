package Day_11;
//throw:发现某个情况不否和要求，希望主动热那个程序产生异常即throw是主动抛出一个异常；
//throws：告诉调用的人，这个方法可能产生某种异常；
//e.getMessage()返回类似hrow new RuntimeException("年龄不能是负数！");括号里的内容
//新认识异常：IllegalArgumentException:参数不合法、NullPointerException.
public class Main {
    public static void main(String[] args){

        try{
            checkScores(-10);
        }catch(IllegalArgumentException e){
            System.out.println("发现异常： "+e.getMessage());
        }
        System.out.println("程序结束");
    }
    public static void checkScores(int score){
        if(score<0||score>100){
            throw new IllegalArgumentException("成绩不合法！");
        }
        System.out.println(score);
    }
}
