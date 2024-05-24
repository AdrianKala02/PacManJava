import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame{
    JLabel titleInMainMenu;
    MyButton newGameButton,highScoreButton,exitButton;
    JPanel panel;
    public MainMenu(){
        setMinimumSize(new Dimension(400, 400));
        setTitle("main menu");
        newGameButton =new MyButton("NEW GAME");
        newGameButton.addActionListener(e->{SwingUtilities.invokeLater(()->new GameMapChooser());dispose();});

        highScoreButton= new MyButton("HIGH SCORE");
        highScoreButton.addActionListener(e->{SwingUtilities.invokeLater(()->new HighScore());dispose();});

        exitButton= new MyButton("EXIT");
        exitButton.addActionListener(e -> dispose());

        titleInMainMenu= new JLabel("The Pac-Man", SwingConstants.CENTER);


        panel= new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(4,1));

        //add to panel
        panel.add(titleInMainMenu);
        panel.add(newGameButton);
        panel.add(highScoreButton);
        panel.add(exitButton);


        add(panel,BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}