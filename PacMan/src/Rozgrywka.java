import javax.swing.*;
import java.awt.*;

public class Rozgrywka extends JPanel implements Runnable {

    private final long fps=1000/144;
    private int ponkty;
    private int zycia;
    boolean przegrana;

    Hero hero;

    ColisionHandler colisionHandler;
    //metody dla ponktow
    public int getPonkty() {return ponkty;}
    public void setPonkty(int ponkty) {this.ponkty = ponkty;}
    public void addPonkty(int ponkty){this.ponkty+=ponkty;}

    //metody dla zyc
    public int getZycie() {return zycia;}
    public void setZycie(int zycia) {this.zycia = zycia;}
    public void addZycie(int zycia) {this.zycia+=zycia;}
    AnimateHandler heroAnimateHandler;
    Rozgrywka(){
        ponkty=0;
        zycia=3;
        przegrana=false;
        setBackground(new Color(98, 158, 225));
        setFocusable(true);
        hero=new Hero("/Users/adriankala/Desktop/PacManAsets/PacMan/SpriteSheet-PacMan2.png");
        colisionHandler=new ColisionHandler(hero);
        heroAnimateHandler= new AnimateHandler(hero.spriteSheet,hero,300,ANIAMTIONTYPE.ANIMATIONLOOP);

    }
    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        g2d.drawImage(hero.sprite,hero.getPosX(),hero.getPosY(),null);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread()+" "+getClass().getName());
        while (!przegrana){
            try {
                Thread.sleep(fps);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            colisionHandler.colisionToWindow();
            hero.update();
            repaint();




        }
    }
}
