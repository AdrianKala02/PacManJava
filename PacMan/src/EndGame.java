import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EndGame extends MyJFrame {
    EndGame(int WszystkiePonkty,String nrMapy){
        JPanel panel= new JPanel();
        JLabel labelWynik= new JLabel("Twój wynik: "+WszystkiePonkty,SwingConstants.CENTER);
        JLabel labelPytanie= new JLabel("Podaj Proszę swój nick",SwingConstants.CENTER);
        JTextField pobieraczNicku= new JTextField();
        MyButton exitButton = new MyButton("EXIT AND SAVE");
        exitButton.addActionListener(e->{
            String nickGivenByUser=pobieraczNicku.getText();

            PlayerScore playerScore=new PlayerScore(nickGivenByUser,WszystkiePonkty,nrMapy);
            ReadAndWriteObj<PlayerScore> readAndWriteObj= new ReadAndWriteObj<>("ScoreBoard.ser");
            readAndWriteObj.writeItEnchanted(playerScore);
            dispose();
        });

        panel.add(labelWynik);
        panel.add(labelPytanie);
        panel.add(pobieraczNicku);
        panel.add(exitButton);
        panel.setLayout(new GridLayout(panel.getComponentCount(),0));
        add(panel);
    }
}
