import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Rozgrywka extends JPanel implements Runnable {

    private final long fps = 1000 / 144;
    private int wszystkiePonkty;
    private int zycia;
    private boolean przegrana;
    public void setPrzegrana(){przegrana=false;}

    public Hero hero;
    Blok blokA;
    PointToCollect pointA;
    Map mapaTest1;
    public int getWszystkiePonkty() {return wszystkiePonkty;}
    public void setWszystkiePonkty(int wszystkiePonkty) {this.wszystkiePonkty = wszystkiePonkty;}
    public int getHeroPoints(){return hero.getPonkty();}
    public int getZycie() { return zycia; }
    public void setZycie(int zycia) { this.zycia = zycia; }
    public void addZycie(int zycia) { this.zycia += zycia; }

    AnimateHandler heroAnimateHandler;

    Rozgrywka() {
        zycia = 3;
        przegrana = false;
        setBackground(new Color(98, 158, 225));
        setFocusable(true);
        setLayout(new GridBagLayout());
        hero = new Hero("/Users/adriankala/Desktop/PacManAsets/PacMan/SpriteSheet-PacMan3.png", new ColorRGB(0, 255, 0), 'H');
        blokA = new Blok("/Users/adriankala/Desktop/PacManAsets/Wall/wall.png", new ColorRGB(0, 0, 0), 'B');
        pointA=new PointToCollect(1,"/Users/adriankala/Desktop/PacManAsets/Other/Point.png",new ColorRGB(0,0,255),'P');
        //AnimateHandler wykorzystuje już w sobie nowy wątek
        heroAnimateHandler = new AnimateHandler(hero.spriteSheet, hero, 100, ANIAMTIONTYPE.ANIMATIONPINGPONG);


        mapaTest1 = new Map("/Users/adriankala/Desktop/PacManAsets/Maps/testMap1.png", blokA, hero,pointA);
        mapaTest1.inicjal(this);
        Thread th=new Thread(mapaTest1);
        th.start();

        addKeyListener(hero);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + " " + getClass().getName());
        while (!przegrana) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            hero.updatePos(mapaTest1.getGritCharMap());

            if(mapaTest1.allPointsCollected(pointA)){
                System.out.println("//====DONE====//");
                wszystkiePonkty+=hero.getPonkty();
                hero.setPonkty(0);
                removeAll();
                revalidate();
                repaint();
                hero.setAclelerationY(0);
                hero.setAclelerationX(0);
                mapaTest1.inicjal(this);

            };
        }
    }
}
