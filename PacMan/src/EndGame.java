import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EndGame extends MyJFrame {
    EndGame(int WszystkiePonkty){
        JPanel panel= new JPanel();
        JLabel label= new JLabel();
        label.setText("Twój wynik: "+WszystkiePonkty);


        panel.add(label);
        add(panel);

        PlayerScore playerScore=new PlayerScore("Fuzzy",WszystkiePonkty,1);

        try {
            FileOutputStream fileOut = new FileOutputStream("ScoreBoard.ser");
            ObjectOutputStream out= new ObjectOutputStream(fileOut);
            out.writeObject(playerScore);
            out.close();
            fileOut.close();
            System.out.println("Player score saved");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
