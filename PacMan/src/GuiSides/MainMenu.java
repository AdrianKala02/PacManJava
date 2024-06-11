package GuiSides;

import MyGui.MyButton;
import MyGui.MyJFrame;
import MyGui.MyJPanel;
import MyGui.MyJlabel;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends MyJFrame {
    MyJlabel titleInMainMenu;
    MyButton newGameButton,highScoreButton,exitButton;
    MyJPanel panel;
    public MainMenu(){
        setTitle("main menu");
        newGameButton =new MyButton("NEW GAME");
        newGameButton.addActionListener(e->{SwingUtilities.invokeLater(()->new GameMapChooser());dispose();});

        highScoreButton= new MyButton("HIGH SCORE");
        highScoreButton.addActionListener(e->{SwingUtilities.invokeLater(()->new HighScore());dispose();});

        exitButton= new MyButton("EXIT");
        exitButton.addActionListener(e -> {dispose();System.exit(0);});
        titleInMainMenu= new MyJlabel();
        titleInMainMenu.setText("The Pac-Man");
        titleInMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
        panel= new MyJPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(4,1));

        //add to panel
        panel.add(titleInMainMenu);
        panel.add(newGameButton);
        panel.add(highScoreButton);
        panel.add(exitButton);

        add(panel,BorderLayout.CENTER);
    }
}