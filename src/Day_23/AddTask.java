package Day_23;

public class AddTask implements Runnable{
    Counter counter= new Counter();
    public AddTask(Counter counter){
        this.counter=counter;
    }
    @Override
    public void run(){
        for(int i=0;i<10000;i++){
            counter.add();
        }
    }
}
