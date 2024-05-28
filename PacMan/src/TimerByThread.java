import static java.util.Objects.nonNull;

public class TimerByThread extends Thread{
    //Tworzy nowy thread(w game) i co sekundę dodaje 1 do całości czyli zlicza sekundy
    private boolean alive;
    private int zegar;
    private int updateInterval;
    TimerByThread(){
        updateInterval=1000;
        alive=true;
        zegar=0;
    }

    TimerByThread(int updateInterval){
        this.updateInterval=updateInterval;
        alive=true;
        zegar=0;
    }
   public int getTime(){
       return zegar;
   }

   public void restartTime(){zegar=0;}
    public void setTime(int czas){zegar=czas;}
   public void stopIt(){
        alive=false;
   };
    @Override
    public void run() {
        System.out.println(Thread.currentThread()+" "+getClass().getName());
       while (alive) {
           try {
               Thread.sleep(updateInterval);
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
               throw new RuntimeException(e);
           }
           System.out.println(zegar);
           zegar++;
       }
    }
}
