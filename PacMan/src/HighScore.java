import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HighScore extends JFrame implements Runnable {
    boolean alive=true;
    JLabel titleInHighScore;
    JScrollPane scrollPane;
    JList lista;
    JPanel tablica;
    HighScore(){




        Integer[] a={12,13,14,15};



        lista=new JList<>(a);
        lista.setLayoutOrientation(JList.VERTICAL);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(lista);

        tablica= new JPanel();
        tablica.setLayout(new GridLayout(0,1));

        titleInHighScore= new JLabel("NAJLEPSI");

        tablica.add(titleInHighScore);
        tablica.add(scrollPane);



        setTitle("Tablica Wynikow");
        setMinimumSize(new Dimension(400, 400));
        setVisible(true);
        add(tablica);

    }

    @Override
    public void run() {

    }
}
