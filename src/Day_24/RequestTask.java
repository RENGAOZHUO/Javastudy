package Day_24;

public class RequestTask implements Runnable{
    int requestid;
    public RequestTask(int requestid){
        this.requestid=requestid;
    }
    @Override
    public void run(){
        System.out.println(
                Thread.currentThread().getName()+
                        "开始处理请求"+requestid
        );
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
