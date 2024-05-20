import javax.swing.*;

public class HighScore extends Thread {
    boolean alive=true;
    JFrame tablicaWynikow;
    JLabel licznikZegar;
    HighScore(){
        tablicaWynikow= new JFrame();
        tablicaWynikow.setTitle("Tablica Wynikow");
        tablicaWynikow.setVisible(true);
        licznikZegar= new JLabel();
        tablicaWynikow.add(licznikZegar);

    }

    @Override
    public void run() {
        TimerByThread timer = new TimerByThread();
        //timer.start();

        while (alive) {
            this.licznikZegar.setText(String.valueOf(timer.getTime()));
        }
    }
}
