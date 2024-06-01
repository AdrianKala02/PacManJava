import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class HighScore extends MyJFrame{
    boolean alive=true;
    JLabel titleInHighScore;
    JScrollPane scrollPane;
    JList<PlayerScore> lista;

    ArrayList<PlayerScore> players;
    JPanel tablica;
    MyButton returnButton;
    HighScore(){

        ReadAndWriteObj<PlayerScore> readAndWriteObj=new ReadAndWriteObj<>("ScoreBoard.ser");
        if(!readAndWriteObj.isExistFile()){
            lista=new JList<>();
            titleInHighScore= new JLabel("Brak Histori najlepszych graczy");
        }else {
            players=readAndWriteObj.readIt();
            DefaultListModel<PlayerScore> bump=new DefaultListModel<>();

            for(PlayerScore ps:players){
              bump.addElement(ps);
            }
            lista = new JList<>(bump);
            titleInHighScore = new JLabel("Najlepsi");
        }
        lista.setLayoutOrientation(JList.VERTICAL);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(lista);

        tablica= new JPanel();
        tablica.setLayout(new GridLayout(0,1));



        returnButton=new MyButton("return");
        returnButton.addActionListener(e->{
            SwingUtilities.invokeLater(()->new MainMenu());
            dispose();
        });


        tablica.add(titleInHighScore);
        tablica.add(scrollPane);
        tablica.add(returnButton);

        setTitle("Tablica Wynikow");
        add(tablica);
    }
}
