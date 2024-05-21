import javax.swing.*;

public class GameMapChooser extends JFrame {
    MyButton uruchomienieButton;
    JPanel panel,panelButtonow;
    JLabel label;
    GameMapChooser(){
        setTitle("Wybór mapy");


        label=new JLabel("Proszę wybrać mapę");



        JRadioButton mapa1=new JRadioButton("map1");
        JRadioButton mapa2=new JRadioButton("map2");
        JRadioButton mapa3=new JRadioButton("map3");
        JRadioButton mapa4=new JRadioButton("map4");
        JRadioButton mapa5=new JRadioButton("map5");


        ButtonGroup grupaMap= new ButtonGroup();
        grupaMap.add(mapa1);
        grupaMap.add(mapa2);
        grupaMap.add(mapa3);
        grupaMap.add(mapa4);
        grupaMap.add(mapa5);



        uruchomienieButton= new MyButton("uruchom grę");
        uruchomienieButton.addActionListener(e->SwingUtilities.invokeLater(()->new Game()));






        panel=new JPanel();

        panelButtonow=new JPanel();
        panelButtonow.add(mapa1);
        panelButtonow.add(mapa2);
        panelButtonow.add(mapa3);
        panelButtonow.add(mapa4);
        panelButtonow.add(mapa5);


        panel.add(label);
        panel.add(panelButtonow);
        panel.add(uruchomienieButton);
        add(panel);

        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
