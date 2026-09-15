package Day_23;

public class Counter {
   int count=0;
   public synchronized void add(){
       count++;
   }
}
