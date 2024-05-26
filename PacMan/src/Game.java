import javax.swing.*;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame{
    JPanel panel;
    JLabel tabliczkaCzasu,tabliczkaPunktow,tabliczkaZyc;
    volatile boolean alive;
    TimerByThread licznik;
    int ponkty;
    MyButton returnButton;
    Rozgrywka rozgrywka;

    Updater<Integer> u1;
    Updater<Integer> u2;
    Updater<Integer> u3;
            Game(){
        ponkty=0;
        alive=true;
        setTitle("ROZGRYWKA");
        setMinimumSize(new Dimension(400, 400));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel= new JPanel();
        panel.setLayout(new GridLayout(0,4));


        tabliczkaCzasu= new JLabel();
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
            SwingUtilities.invokeLater(()->new MainMenu());
            dispose();
        });



       //SwingUtilities.invokeLater(()->rozgrywka=new Rozgrywka());
        rozgrywka=new Rozgrywka();
        Thread thread=new Thread(rozgrywka);
        thread.start();

        licznik = new TimerByThread();
        new Thread(licznik).start();


u1= new Updater<>(licznik::getTime, time -> tabliczkaCzasu.setText("Czas: " + time),300);u1.start();
u2= new Updater<>(rozgrywka::getPonkty, pts -> tabliczkaPunktow.setText("Punkty: " + pts), 300);u2.start();
u3= new Updater<>(rozgrywka::getZycie, lives -> tabliczkaZyc.setText("Ilosc Zyc: " + lives), 300);u3.start();




        panel.add(tabliczkaCzasu);
        panel.add(tabliczkaPunktow);
        panel.add(tabliczkaZyc);
        panel.add(returnButton);


        add(panel,"North");
        add(rozgrywka,"Center");

    }
}
