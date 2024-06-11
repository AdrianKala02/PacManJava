package GuiSides;

import MyGui.MyButton;
import MyGui.MyJFrame;
import MyGui.MyJPanel;
import MyGui.MyJlabel;
import toolBox.TimerByThread;
import toolBox.Updater;

import javax.swing.*;
import java.awt.*;

public class Game extends MyJFrame {
    MyJPanel panel;
    MyJlabel tabliczkaCzasu,tabliczkaWszystkichPunktow,tabliczkaPunktow,tabliczkaZyc;
    volatile boolean alive;
    TimerByThread licznik;
    int ponkty;
    MyButton returnButton;
    Rozgrywka rozgrywka;

    Updater<Integer> u1;
    Updater<Integer> u2;
    Updater<Integer> u3;
    Updater<Integer> u4;
    String mapUrl;
    Game(String mapUrl,String rodzajRozgrywki){
        this.mapUrl=mapUrl;
        ponkty=0;
        alive=true;
        setTitle("ROZGRYWKA");

        setLayout(new BorderLayout());
        panel= new MyJPanel();
        panel.setLayout(new GridLayout(1,5));
        tabliczkaCzasu= new MyJlabel();
        tabliczkaWszystkichPunktow= new MyJlabel();
        tabliczkaPunktow= new MyJlabel();
        tabliczkaZyc =new MyJlabel();

        returnButton= new MyButton("return");
        returnButton.addActionListener(e ->{
            rozgrywka.rezygnacjaZWyboru=true;
            alive=false;
            System.out.println("YOU NEED TO STOP THE CLOCK");
            licznik.stopIt();
            u1.stopIt();
            u2.stopIt();
            u3.stopIt();
            u4.stopIt();
            rozgrywka.stopIt();
            SwingUtilities.invokeLater(()->new MainMenu());
            dispose();
        });


        rozgrywka=new Rozgrywka(mapUrl,rodzajRozgrywki);
        Thread thread=new Thread(rozgrywka);
        thread.start();

        returnButton.addKeyListener(rozgrywka.hero);

        licznik = new TimerByThread();
        new Thread(licznik).start();

        u1= new Updater<>(licznik::getTime, time -> tabliczkaCzasu.setText("Czas: " + time),300);u1.start();
        u2= new Updater<>(rozgrywka::getWszystkiePonkty, pts -> tabliczkaWszystkichPunktow.setText("Wsz. Pk: " + pts),300);u2.start();
        u3= new Updater<>(rozgrywka::getHeroPoints, pts -> tabliczkaPunktow.setText("Pk: " + pts), 300);u3.start();
        u4= new Updater<>(rozgrywka::getHeroHP, lives -> tabliczkaZyc.setText("Życia: " + lives), 300);u4.start();

        Thread tr= new Thread(()->{
            while (!rozgrywka.isPrzegrana()){
                //!// System.out.println(Thread.currentThread()+" "+getClass().getName());
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){e.fillInStackTrace();}
            }
            //it takes url and takes what is after last '/' so the file name
            //then splits string with dot and takes first element of []
            String nameOfTheFile=mapUrl.substring(mapUrl.lastIndexOf("/") + 1).split("\\.")[0];
            if(!rozgrywka.rezygnacjaZWyboru) {
                SwingUtilities.invokeLater(() -> new EndGame(rozgrywka.getWszystkiePonkty(), nameOfTheFile,rodzajRozgrywki));
            }
            System.out.println("GAME OVER");
            u1.stopIt();
            u2.stopIt();
            u3.stopIt();
            u4.stopIt();
            licznik.stopIt();
            dispose();
        });
        tr.start();
        panel.add(tabliczkaCzasu);
        panel.add(tabliczkaWszystkichPunktow);
        panel.add(tabliczkaPunktow);
        panel.add(tabliczkaZyc);
        panel.add(returnButton);


        add(panel,"North");
        SwingUtilities.invokeLater( ()->add(rozgrywka,"Center"));


    }
}
