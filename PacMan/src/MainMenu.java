import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    JLabel titleInMainMenu;
    MyButton newGameButton,highScoreButton,exitButton;
    JPanel panel;
    public MainMenu(){
        setMinimumSize(new Dimension(400, 400));
        setTitle("main menu");



        newGameButton =new MyButton("NEW GAME");
        newGameButton.addActionListener(e->SwingUtilities.invokeLater(()->new Game()));

        highScoreButton= new MyButton("HIGH SCORE");
        highScoreButton.addActionListener(e->SwingUtilities.invokeLater(()->new HighScore()));

        exitButton= new MyButton("EXIT");
        exitButton.addActionListener(e -> dispose());

        titleInMainMenu= new JLabel("The Pac-Man", SwingConstants.CENTER);
        titleInMainMenu.setForeground(new Color(222, 22, 22));
        titleInMainMenu.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        titleInMainMenu.setBackground(new Color(12,12,12));
        titleInMainMenu.setOpaque(true);
        titleInMainMenu.setMinimumSize(new Dimension(100,50));
        titleInMainMenu.setPreferredSize(new Dimension(100,20));
        titleInMainMenu.setMaximumSize(new Dimension(100,50));
        titleInMainMenu.setBorder(BorderFactory.createLineBorder(new Color(19, 13, 204),9));


        panel= new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(4,1));

        //add to panel
        panel.add(titleInMainMenu);
        panel.add(newGameButton);
        panel.add(highScoreButton);
        panel.add(exitButton);


        add(panel,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}