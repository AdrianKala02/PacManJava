import static java.util.Objects.nonNull;

public class TimerByThread extends Thread{


    //Tworzy nowy thread(w game) i co sekundę dodaje 1 do całości czyli zlicza sekundy
    private boolean alive;
    private int zegar;

    private int miliseconds;
    TimerByThread(){
        miliseconds=1000;
         alive=true;
         zegar=0;
    }

    TimerByThread(int miliseconds){
        this.miliseconds=miliseconds;
        alive=true;
        zegar=0;
    }
   public int getTime(){
       return zegar;
   }

   public void stopIt(){
        alive=false;
   };
    @Override
    public void run() {
        System.out.println(Thread.currentThread()+" "+getClass().getName());
       while (alive) {
           try {
               Thread.sleep(miliseconds);
           } catch (InterruptedException e) {
               System.out.println("DUPA");
               Thread.currentThread().interrupt();
               throw new RuntimeException(e);
           }
           System.out.println(zegar);
           zegar++;
       }
    }
}
