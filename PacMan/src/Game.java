import javax.swing.*;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Handler;

public class Game extends JFrame{
    JPanel panel;
    JLabel tabliczkaCzasu,tabliczkaWszystkichPunktow,tabliczkaPunktow,tabliczkaZyc;
    volatile boolean alive;
    TimerByThread licznik;
    int ponkty;
    MyButton returnButton;
    Rozgrywka rozgrywka;

    Updater<Integer> u1;
    Updater<Integer> u2;
    Updater<Integer> u3;
    Updater<Integer> u4;
    Game(){
        ponkty=0;
        alive=true;
        setTitle("ROZGRYWKA");
        setMinimumSize(new Dimension(600, 600));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        panel= new JPanel();
        panel.setLayout(new GridLayout(0,5));
        tabliczkaCzasu= new JLabel();
        tabliczkaWszystkichPunktow= new JLabel();
        tabliczkaPunktow= new JLabel();
        tabliczkaZyc =new JLabel();

        returnButton= new MyButton("return");
        returnButton.addActionListener(e ->{
            alive=false;
            System.out.println("YOU NEED TO STOP THE CLOCK");
            licznik.stopIt();
            u1.stopIt();
            u2.stopIt();
            u3.stopIt();
            u4.stopIt();
            rozgrywka.setPrzegrana();
            SwingUtilities.invokeLater(()->new MainMenu());
            dispose();
        });


        rozgrywka=new Rozgrywka();
        Thread thread=new Thread(rozgrywka);
        thread.start();

        returnButton.addKeyListener(rozgrywka.hero);

        licznik = new TimerByThread();
        new Thread(licznik).start();


        u1= new Updater<>(licznik::getTime, time -> tabliczkaCzasu.setText("Czas: " + time),300);u1.start();
        u2= new Updater<>(rozgrywka::getWszystkiePonkty, pts -> tabliczkaWszystkichPunktow.setText("Wszystkie Punkty: " + pts),300);u2.start();
        u3= new Updater<>(rozgrywka::getHeroPoints, pts -> tabliczkaPunktow.setText("Punkty: " + pts), 300);u3.start();
        u4= new Updater<>(rozgrywka::getZycie, lives -> tabliczkaZyc.setText("Ilosc Zyc: " + lives), 300);u4.start();




        panel.add(tabliczkaCzasu);
        panel.add(tabliczkaWszystkichPunktow);
        panel.add(tabliczkaPunktow);
        panel.add(tabliczkaZyc);
        panel.add(returnButton);


        add(panel,"North");
        SwingUtilities.invokeLater( ()->add(rozgrywka,"Center"));

    }
}
