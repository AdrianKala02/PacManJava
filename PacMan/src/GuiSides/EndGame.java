package GuiSides;

import MyGui.*;
import serializatonMy.PlayerScore;
import serializatonMy.ReadAndWriteObj;

import javax.swing.*;
import java.awt.*;


public class EndGame extends MyJFrame {
    EndGame(int WszystkiePonkty,String nrMapy){
        JPanel panel= new JPanel();
        MyJlable labelWynik= new MyJlable();
        labelWynik.setText("Twój wynik: "+WszystkiePonkty);
        labelWynik.setHorizontalAlignment(0);
        MyJlable labelPytanie= new MyJlable();
        labelPytanie.setHorizontalAlignment(0);
        labelPytanie.setText("Podaj Proszę swój nick");
        JTextField pobieraczNicku= new JTextField();
        MyButton exitButton = new MyButton("EXIT AND SAVE");
        exitButton.addActionListener(e->{
            if(!pobieraczNicku.getText().trim().isEmpty()){
            String nickGivenByUser=pobieraczNicku.getText();
            PlayerScore playerScore=new PlayerScore(nickGivenByUser,WszystkiePonkty,nrMapy);
            ReadAndWriteObj<PlayerScore> readAndWriteObj= new ReadAndWriteObj<>("ScoreBoard.ser");
            readAndWriteObj.writeItEnchanted(playerScore);
           dispose();
           System.exit(0);
            }else{
                JOptionPane.showMessageDialog(this,"proszę podać nazwę","brak nazwy",JOptionPane.PLAIN_MESSAGE);
            }
        });

        panel.add(labelWynik);
        panel.add(labelPytanie);
        panel.add(pobieraczNicku);
        panel.add(exitButton);
        panel.setLayout(new GridLayout(panel.getComponentCount(),0));
        add(panel);
    }
}
