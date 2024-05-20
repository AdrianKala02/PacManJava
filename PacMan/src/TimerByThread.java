public class TimerByThread extends Thread{


    private boolean alive;
    private int zegar;
    TimerByThread(){
         alive=true;
         zegar=0;
    }
   public int getTime(){
       return zegar;
   }
    @Override
    public void run() {
       while (alive) {
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
           zegar++;
       }
    }
}
