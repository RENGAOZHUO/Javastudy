package Day_24;

public class MyTask implements Runnable{

    int id;
    public MyTask(int id){
        this.id=id;
    }
    @Override
    public void run(){
        System.out.println(
                Thread.currentThread().getName()+
                        "正在执行任务:"+"任务"+id
        );
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }


}
