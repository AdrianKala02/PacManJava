package GuiSides;

import MyGui.MyButton;
import MyGui.MyJFrame;
import MyGui.MyJPanel;
import MyGui.MyJlable;
import serializatonMy.PlayerScore;
import serializatonMy.ReadAndWriteObj;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HighScore extends MyJFrame {
    boolean alive=true;
    MyJlable titleInHighScore;
    JScrollPane scrollPane;
    JList<PlayerScore> lista;

    ArrayList<PlayerScore> players;
    MyJPanel tablica;
    MyButton returnButton;
    HighScore(){

        ReadAndWriteObj<PlayerScore> readAndWriteObj=new ReadAndWriteObj<>("ScoreBoard.ser");
        titleInHighScore= new MyJlable();
        titleInHighScore.setHorizontalAlignment(SwingConstants.CENTER);
        if(!readAndWriteObj.isExistFile()){
            lista=new JList<>();
            titleInHighScore.setText("Brak Histori najlepszych graczy");

        }else {
            players=readAndWriteObj.readIt();

            players.sort(PlayerScore::compareTo);
            DefaultListModel<PlayerScore> bump=new DefaultListModel<>();
            for(PlayerScore ps:players){
              bump.addElement(ps);
            }
            lista = new JList<>(bump);
            lista.setBackground(new Color(47, 72, 92));
            lista.setFont(new Font(Font.SERIF,Font.PLAIN,20));
            lista.setForeground(new Color(245,169,91));


            DefaultListCellRenderer renderer =  (DefaultListCellRenderer)lista.getCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
            titleInHighScore.setText("Najlepsi");
        }
        lista.setLayoutOrientation(JList.VERTICAL);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(lista);

        tablica= new MyJPanel();
        tablica.setLayout(new GridLayout(0,1));



        returnButton=new MyButton("return");
        returnButton.addActionListener(e->{
            SwingUtilities.invokeLater(()->new MainMenu());
            dispose();
        });


        tablica.add(titleInHighScore);
        tablica.add(scrollPane);
        tablica.add(returnButton);

        setTitle("Tablica Wynikow");
        add(tablica);
    }
}
