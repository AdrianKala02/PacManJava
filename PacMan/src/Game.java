import javax.swing.*;
import java.awt.*;

public class Game extends Thread {
    JFrame gra;
    JPanel panel;
    JLabel tabliczkaCzasu,tabliczkaPunktow,tabliczkaZyc;
    boolean alive;
    TimerByThread licznik;
    int ponkty;

    Rozgrywka rozgrywka;
    Game(){
        ponkty=0;
        alive=true;
        gra= new JFrame();
        gra.setTitle("ROZGRYWKA");
        gra.setVisible(true);
        gra.setLayout(new BorderLayout());

        panel= new JPanel();
        panel.setLayout(new GridLayout(0,3));


        tabliczkaCzasu= new JLabel();
        tabliczkaPunktow= new JLabel();
        tabliczkaZyc =new JLabel();

        panel.add(tabliczkaCzasu);
        panel.add(tabliczkaPunktow);
        panel.add(tabliczkaZyc);

        gra.add(panel,"North");
        rozgrywka=new Rozgrywka();
        gra.add(rozgrywka,"Center");
        Thread thread=new Thread(rozgrywka);
        thread.start();

        licznik = new TimerByThread();
        new Thread(licznik).start();

        Updater<Integer> czas = new Updater<>(licznik::getTime, time -> tabliczkaCzasu.setText("Czas: " + time),1000);
        czas.start();

        Updater<Integer> punktyUpdater = new Updater<>(rozgrywka::getPonkty, pts -> tabliczkaPunktow.setText("Punkty: " + pts), 50);
        punktyUpdater.start();

        Updater<Integer> zyciaUpdater = new Updater<>(rozgrywka::getZycie, lives -> tabliczkaZyc.setText("Ilosc Zyc: " + lives), 50);
        zyciaUpdater.start();

    }

    @Override
    public void run() {
        while (alive) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            rozgrywka.addPonkty(10);
        }

    }
}
