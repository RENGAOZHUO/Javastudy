package Day_26;

import java.util.concurrent.Callable;

public class SumTask implements Callable<Integer> {//implements Callable<Integer>
                            //这里：<Integer>表示：这个任务最后会返回一个 Integer。
    int start;
    int end;
    public SumTask (int start,int end){
        this.start=start;
        this.end=end;
    }
    @Override
    public Integer call() throws InterruptedException {//第二，方法不再叫：run(),而叫：call()
        int sum=0;
        for(int i=start;i<=end;i++){
            sum+=i;
        }
        return sum;//第三：public Integer call()有返回值，所以可以：return sum;
    }






}
