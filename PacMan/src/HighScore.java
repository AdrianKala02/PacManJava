import javax.swing.*;
import java.awt.*;

public class HighScore extends Thread {
    boolean alive=true;
    JFrame tablicaWynikow;
    JLabel titleInHighScore;

    JPanel tablica;
    HighScore(){
        tablicaWynikow= new JFrame();
        tablicaWynikow.setTitle("Tablica Wynikow");
        tablicaWynikow.setVisible(true);
        titleInHighScore= new JLabel();
        titleInHighScore.setText("NAJLEPSI");
        tablica= new JPanel();
        tablica.setLayout(new GridLayout(1,0));
        tablica.add(titleInHighScore);
        tablicaWynikow.add(tablica);

    }

    @Override
    public void run() {

    }
}
