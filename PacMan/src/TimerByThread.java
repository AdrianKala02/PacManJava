public class TimerByThread extends Thread{


    //Tworzy nowy thread(w game) i co sekundę dodaje 1 do całości czyli zlicza sekundy
    //jeżeli zauważy się przeskok o 1 sekundę np z 3 na 5 to wina jest tego,
    // że update też jest aktualizowany co 1 sekunde, przy wszybszej aktualizacj nie zauważono takiego problemu
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
    @Override
    public void run() {
       while (alive) {
           try {
               Thread.sleep(miliseconds);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
           zegar++;
       }
    }
}
