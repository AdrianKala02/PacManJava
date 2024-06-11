package GuiSides;

import MyGui.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GameMapChooser extends MyJFrame {
    public String mapNameSize(String url) {
        try {
            return ImageIO.read(new File(url)).getHeight()+" X "+ ImageIO.read(new File(url)).getWidth();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "noFIle";
    }
   private MyButton uruchomienieButton;
   private MyJPanel panel,panelButtonow,panelButtonowRodzajRozgrywki;
   private MyJlabel label,label2;
   private ButtonGroup grupaMap,rodzajGry;
    GameMapChooser(){
        panel=new MyJPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(6,1));
        setTitle("Wybór mapy");
        label=new MyJlabel();
        label.setText("Proszę wybrać mapę");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        MyJRadioButton mapa1=new MyJRadioButton("map1");
        mapa1.setActionCommand("./PacManAsets/Maps/testMap1.png");
        mapa1.setText(mapNameSize(mapa1.getActionCommand()));
        mapa1.setSelected(true);

        MyJRadioButton mapa2=new MyJRadioButton("map2");
        mapa2.setActionCommand("./PacManAsets/Maps/testMap2.png");
        mapa2.setText(mapNameSize(mapa2.getActionCommand()));
        MyJRadioButton mapa3=new MyJRadioButton("map3");
        mapa3.setActionCommand("./PacManAsets/Maps/testMap3.png");
        mapa3.setText(mapNameSize(mapa3.getActionCommand()));
        MyJRadioButton mapa4=new MyJRadioButton("map4");
        mapa4.setActionCommand("./PacManAsets/Maps/testMap4.png");
        mapa4.setText(mapNameSize(mapa4.getActionCommand()));
        MyJRadioButton mapa5=new MyJRadioButton("map5");
        mapa5.setActionCommand("./PacManAsets/Maps/testMap5.png");
        mapa5.setText(mapNameSize(mapa5.getActionCommand()));
        grupaMap= new ButtonGroup();
        grupaMap.add(mapa1);
        grupaMap.add(mapa2);
        grupaMap.add(mapa3);
        grupaMap.add(mapa4);
        grupaMap.add(mapa5);


        label2=new MyJlabel();
        label2.setText("Proszę wybrać rodzaj rozgrywki");
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        MyJRadioButton rodzaj1=new MyJRadioButton("normalny");
        MyJRadioButton rodzaj2=new MyJRadioButton("więcej hp, bez restartu");


        rodzajGry=new ButtonGroup();
        rodzajGry.add(rodzaj1);
        rodzaj1.setActionCommand("normal");
        rodzaj1.setSelected(true);
        rodzajGry.add(rodzaj2);
        rodzaj2.setActionCommand("newVer");


        uruchomienieButton= new MyButton("uruchom grę");
        uruchomienieButton.addActionListener(e->{this.dispose();SwingUtilities.invokeLater(()->new Game(grupaMap.getSelection().getActionCommand(),rodzajGry.getSelection().getActionCommand()));});

        panelButtonow=new MyJPanel();
        panelButtonow.add(mapa1);
        panelButtonow.add(mapa2);
        panelButtonow.add(mapa3);
        panelButtonow.add(mapa4);
        panelButtonow.add(mapa5);

        panelButtonowRodzajRozgrywki=new MyJPanel();
        panelButtonowRodzajRozgrywki.add(rodzaj1);
        panelButtonowRodzajRozgrywki.add(rodzaj2);

        setLayout(new GridLayout(0,1));
        panel.add(label);
        panel.add(panelButtonow);
        panel.add(label2);
        panel.add(panelButtonowRodzajRozgrywki);
        panel.add(uruchomienieButton);

        add(panel,BorderLayout.CENTER);
    }
}
