package Day_25;

public class MyTask implements Runnable{
    int id;
    public MyTask(int id){
        this.id=id;
    }
    @Override
    public void run(){
        System.out.println(
                Thread.currentThread().getName()+
                        "正在执行任务："+id
        );
        try{
            Thread.sleep(3000);
        }
    catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(
                Thread.currentThread().getName()+
                        "完成任务"+id
        );
}}
