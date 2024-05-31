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
    JPanel tablica;
    MyButton returnButton;
    HighScore(){
        PlayerScore playerScore=null;
        try {
            FileInputStream fileIn = new FileInputStream("ScoreBoard.ser");
            ObjectInputStream in= new ObjectInputStream(fileIn);
            playerScore=(PlayerScore) in.readObject();
            in.close();
            fileIn.close();
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        PlayerScore[] ps= new PlayerScore[2];
        ps[0]=playerScore;
        lista=new JList<>(ps);
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
        add(tablica);
    }
}
