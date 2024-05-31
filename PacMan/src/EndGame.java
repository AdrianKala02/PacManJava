import javax.swing.*;

public class EndGame extends MyJFrame {
    EndGame(int WszystkiePonkty){
        JPanel panel= new JPanel();
        JLabel label= new JLabel();
        label.setText("Twój wynik: "+WszystkiePonkty);


        panel.add(label);
        add(panel);
    }
}
