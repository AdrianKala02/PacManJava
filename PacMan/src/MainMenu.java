import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener {
    JLabel titleInMainMenu;
    JButton newGameButton;
    JButton highScoreButton;
    JButton exitButton;
    JFrame frame;
    JPanel panel;
    public MainMenu(){
        frame= new JFrame();
        frame.setSize(400,400);
        frame.setTitle("main menu");

        newGameButton =new JButton("NEW GAME");
        newGameButton.addActionListener(e-> new Game().start());
        highScoreButton= new JButton("HIGH SCORE");
        highScoreButton.addActionListener(e -> new HighScore().start());
        exitButton= new JButton("EXIT");
        exitButton.addActionListener(e -> frame.dispose());

        titleInMainMenu= new JLabel("The Pac-Man", SwingConstants.CENTER);
        titleInMainMenu.setBorder(BorderFactory.createBevelBorder(1,new Color(120,10,23),new Color(10, 15, 120),new Color(21, 120, 10),new Color(120, 94, 10)));
        panel= new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(4,1));

        //add to panel
        panel.add(titleInMainMenu);
        panel.add(newGameButton);
        panel.add(highScoreButton);
        panel.add(exitButton);


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