import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener {
    JLabel label;
    JButton NewGameButton;
    JButton HighScoreButton;
    JButton ExitButton;
    JFrame frame;
    JPanel panel;
    int count =0;
    public MainMenu(){
    frame= new JFrame();
    frame.setTitle("main menu");

         NewGameButton =new JButton("NEW GAME");;
         HighScoreButton= new JButton("HIGH SCORE");;
         ExitButton= new JButton("EXIT");;

         label= new JLabel("Number of clicks:"+count);
        panel= new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0,4));

        panel.add(NewGameButton);
        panel.add();
        panel.add();

        panel.add(label);
        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
       new MainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}