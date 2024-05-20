import javax.swing.*;
import java.awt.*;

public class Game extends Thread {
    JFrame gra;
    JPanel panel;
    JLabel tabliczkaCzasu,tabliczkaPunktow,tabliczkaZyc;
    boolean alive;
    TimerByThread licznik;
    int ponkty;
    Game(){
        ponkty=0;
        alive=true;
        gra= new JFrame();
        gra.setTitle("ROZGRYWKA");
        gra.setVisible(true);

        panel= new JPanel();
        panel.setLayout(new GridLayout(0,3));


        tabliczkaCzasu= new JLabel();
        tabliczkaPunktow= new JLabel();
        tabliczkaZyc =new JLabel();

        panel.add(tabliczkaCzasu);
        panel.add(tabliczkaPunktow);
        panel.add(tabliczkaZyc);

        gra.add(panel);


        licznik = new TimerByThread();
        new Thread(licznik).start();

        Updater<Integer> czas = new Updater<>(licznik::getTime, time -> tabliczkaCzasu.setText("Czas: " + time),1000);
        czas.start();

        Updater<Integer> punktyUpdater = new Updater<>(() -> ponkty, pts -> tabliczkaPunktow.setText("Punkty: " + pts), 50);
        punktyUpdater.start();



    }

    @Override
    public void run() {
        while (alive) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ponkty+=10;
        }

    }
}
