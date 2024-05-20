import javax.swing.*;
import java.awt.*;

public class Rozgrywka extends JPanel implements Runnable{
    private int ponkty;
    private int zycia;
    boolean przegrana;
    JButton aa;

    //metody dla ponktow
    public int getPonkty() {return ponkty;}
    public void setPonkty(int ponkty) {this.ponkty = ponkty;}
    public void addPonkty(int ponkty){this.ponkty+=ponkty;}

    //metody dla zyc
    public int getZycie() {return zycia;}
    public void setZycie(int zycia) {this.zycia = zycia;}
    public void addZycie(int zycia) {this.zycia+=zycia;}
    Rozgrywka(){
        ponkty=0;
        zycia=3;
        przegrana=false;
        this.setBackground(new Color(98, 158, 225));
        aa= new JButton("aa");
        aa.addActionListener(e -> ponkty=0);
        this.add(aa);

    }

    @Override
    public void run() {
        while (!przegrana){
            if(zycia<=0){przegrana=!przegrana;}

        }
    }
}
