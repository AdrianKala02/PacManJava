import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HighScore extends JFrame implements Runnable {
    boolean alive=true;
    JLabel titleInHighScore;
    JScrollPane scrollPane;
    JList lista;
    JPanel tablica;
    MyButton returnButton;
    HighScore(){




        Integer[] a={12,13,14,15};



        lista=new JList<>(a);
        lista.setLayoutOrientation(JList.VERTICAL);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(lista);

        tablica= new JPanel();
        tablica.setLayout(new GridLayout(0,1));

        titleInHighScore= new JLabel("NAJLEPSI");

        returnButton=new MyButton("return");
        returnButton.addActionListener(e->{
            SwingUtilities.invokeLater(()->new MainMenu());
            dispose();
        });


        tablica.add(titleInHighScore);
        tablica.add(scrollPane);
        tablica.add(returnButton);


        setTitle("Tablica Wynikow");
        setMinimumSize(new Dimension(400, 400));
        setVisible(true);
        add(tablica);

    }

    @Override
    public void run() {

    }
}
