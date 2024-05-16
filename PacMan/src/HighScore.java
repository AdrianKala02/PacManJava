import javax.swing.*;

public class HighScore implements Runnable {
    HighScore(){
        JFrame tablicaWynikow= new JFrame();
        tablicaWynikow.setTitle("Tablica Wynikow");
        tablicaWynikow.setVisible(true);
    }

    @Override
    public void run() {
       new HighScore();
    }
}
