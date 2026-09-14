package Day_22;

public class DownloadTask implements Runnable{
    @Override
    public void run(){
        int precent=0;
        for(int i=0;i<5;i++){
            precent+=20;

            System.out.println("下载进度"+precent+"%");
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
