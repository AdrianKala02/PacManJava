import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class Rozgrywka extends JPanel implements Runnable {

    private final long fps=1000/144;
    private int ponkty;
    private int zycia;
    boolean przegrana;

    Hero hero;
    Blok blokA;
    Map mapaTest1;
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
    ArrayList<Blok> blokiNO1;
    Rozgrywka(){
        ponkty=0;
        zycia=3;
        przegrana=false;
        setBackground(new Color(98, 158, 225));
        setFocusable(true);

        //inicjalizacja bytów
        hero=new Hero("/Users/adriankala/Desktop/PacManAsets/PacMan/SpriteSheet-PacMan3.png",new ColorRGB(0,255,0));
        blokA=new Blok("/Users/adriankala/Desktop/PacManAsets/Wall/wall.png",new ColorRGB(0,0,0));

        //modyfikacje
        colisionHandler=new ColisionHandler();
        heroAnimateHandler= new AnimateHandler(hero.spriteSheet,hero,100,ANIAMTIONTYPE.ANIMATIONPINGPONG);

        //dodanie bytów do list, najpierw do arraylist dla kategorii a potem do ogólnej 2D
        ArrayList<Blok> bloki= new ArrayList<>();
        bloki.add(blokA);

        blokiNO1=new ArrayList<>();

        //inicjalizacja mapy
        mapaTest1=new Map("/Users/adriankala/Desktop/PacManAsets/Maps/testMap1.png",bloki,blokiNO1,hero);

    }
    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        g2d.drawImage(hero.sprite,hero.getPosX(),hero.getPosY(),null);


        for (Blok blok:blokiNO1){
            g2d.drawImage(blok.sprite,blok.getPosX(),blok.getPosY(),null);
        }
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
            colisionHandler.colisionToWindow(hero);
            colisionHandler.colisionObjToObj(hero,blokiNO1.get(0));
            hero.update();
            repaint();




        }
    }
}
