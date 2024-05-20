import javax.swing.*;
import java.awt.*;

public class Game extends Thread {
    JFrame gra;
    JPanel panel;
    JLabel tabliczkaCzasu;
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
        panel.setLayout(new GridLayout(3,1));


        tabliczkaCzasu= new JLabel();
        JLabel tabliczkaCzasu1= new JLabel();
        JLabel tabliczkaCzasu2 =new JLabel();

        panel.add(tabliczkaCzasu);
        panel.add(tabliczkaCzasu1);
        panel.add(tabliczkaCzasu2);

        gra.add(panel);


        licznik = new TimerByThread();
        new Thread(licznik).start();

        Updater czas = new Updater(licznik::getTime,time -> tabliczkaCzasu.setText("Czas: " + time),1000);
        czas.start();

        Updater punktyUpdater = new Updater(() -> ponkty, pts -> tabliczkaCzasu1.setText("Punkty: " + pts), 50);
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
