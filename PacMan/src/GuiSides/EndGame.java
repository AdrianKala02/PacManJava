package GuiSides;

import MyGui.*;
import serializatonMy.PlayerScore;
import serializatonMy.ReadAndWriteObj;

import javax.swing.*;
import java.awt.*;


public class EndGame extends MyJFrame {
    EndGame(int WszystkiePonkty,String nrMapy,String rodzajRozgrywki){
        MyJPanel panel= new MyJPanel();
        MyJlable labelWynik= new MyJlable();
        labelWynik.setText("Twój wynik: "+WszystkiePonkty);
        labelWynik.setHorizontalAlignment(0);
        MyJlable labelPytanie= new MyJlable();
        labelPytanie.setHorizontalAlignment(0);
        labelPytanie.setText("Podaj Proszę swój nick");
        JTextField pobieraczNicku= new JTextField();
        pobieraczNicku.setBackground(new Color(47, 72, 92));
        pobieraczNicku.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        pobieraczNicku.setForeground(new Color(245,169,91));
        MyButton exitButton = new MyButton("EXIT AND SAVE");
        exitButton.addActionListener(e->{
            if(!pobieraczNicku.getText().trim().isEmpty()){
            String nickGivenByUser=pobieraczNicku.getText();
            PlayerScore playerScore=new PlayerScore(nickGivenByUser,WszystkiePonkty,nrMapy,rodzajRozgrywki);
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
