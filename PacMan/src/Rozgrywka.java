import javax.swing.*;
import java.awt.*;

public class Rozgrywka extends JPanel implements Runnable{
    private int ponkty;
    private int zycia;
    boolean przegrana;
    MyButton pointButtonRestarter;

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
        setBackground(new Color(98, 158, 225));

        pointButtonRestarter= new MyButton("reset");
        pointButtonRestarter.addActionListener(e ->ponkty=0);
        add(pointButtonRestarter);


    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread()+" "+getClass().getName());
        while (!przegrana){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
