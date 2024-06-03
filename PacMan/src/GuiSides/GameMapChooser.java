package GuiSides;

import MyGui.MyButton;
import MyGui.MyJFrame;
import MyGui.MyJRadioButton;
import MyGui.MyJlable;

import javax.swing.*;
import java.awt.*;

public class GameMapChooser extends MyJFrame {
    MyButton uruchomienieButton;
    JPanel panelButtonow;
    MyJlable label;
    ButtonGroup grupaMap;
    GameMapChooser(){
        setTitle("Wybór mapy");
        label=new MyJlable();
        label.setText("Proszę wybrać mapę");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        MyJRadioButton mapa1=new MyJRadioButton("map1");
        mapa1.setActionCommand("./PacManAsets/Maps/testMap1.png");
        mapa1.setSelected(true);
        MyJRadioButton mapa2=new MyJRadioButton("map2");
        mapa2.setActionCommand("./PacManAsets/Maps/testMap2.png");
        MyJRadioButton mapa3=new MyJRadioButton("map3");
        mapa3.setActionCommand("./PacManAsets/Maps/testMap3.png");
        MyJRadioButton mapa4=new MyJRadioButton("map4");
        mapa4.setActionCommand("./PacManAsets/Maps/testMap4.png");
        MyJRadioButton mapa5=new MyJRadioButton("map5");
        mapa5.setActionCommand("./PacManAsets/Maps/testMap5.png");


        grupaMap= new ButtonGroup();
        grupaMap.add(mapa1);
        grupaMap.add(mapa2);
        grupaMap.add(mapa3);
        grupaMap.add(mapa4);
        grupaMap.add(mapa5);



        uruchomienieButton= new MyButton("uruchom grę");
        uruchomienieButton.addActionListener(e->{this.dispose();SwingUtilities.invokeLater(()->new Game(grupaMap.getSelection().getActionCommand()));});

        panelButtonow=new JPanel();
        panelButtonow.add(mapa1);
        panelButtonow.add(mapa2);
        panelButtonow.add(mapa3);
        panelButtonow.add(mapa4);
        panelButtonow.add(mapa5);

        setLayout(new GridLayout(0,1));
        add(label);
        add(panelButtonow);
        add(uruchomienieButton);
    }
}
