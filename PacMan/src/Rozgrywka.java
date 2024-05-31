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
    boolean przegrana;
    Character[][] gritCharMap;
    MyJlable[][] gritGame;
   public Hero hero;
    Blok blokA;
    PointToCollect pointA;
    Map mapaTest1;
    ColisionHandler colisionHandler;


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

        GridBagConstraints gbc = new GridBagConstraints();

        gritCharMap = mapaTest1.getGritCharMap();
        gritGame = new MyJlable[gritCharMap.length][gritCharMap[0].length];

        for (int i = 0; i < gritCharMap.length; i++) {
            for (int j = 0; j < gritCharMap[0].length; j++) {
                MyJlable label = new MyJlable();
                if (gritCharMap[i][j] == hero.getIdChar()) {
                    hero.setPosX(i);
                    hero.setPosY(j);
                    label.setIcon(hero.imageIcon);
                } else if (gritCharMap[i][j] == blokA.getIdChar()) {
                    label.setIcon(blokA.imageIcon);
                }
                else if (gritCharMap[i][j] == pointA.getIdChar()) {
                    label.setIcon(pointA.imageIcon);
                }
                gritGame[i][j] = label;
                gbc.gridx = j;
                gbc.gridy = i;
                add(label, gbc);
            }
        }

        mapaTest1.setGritGame(gritGame);
        mapaTest1.setGritCharMap(gritCharMap);

        Thread th=new Thread(mapaTest1);
        th.start();

        Thread th2= new Thread( ()->hero.updatePos(gritCharMap));
        th2.start();

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

            hero.updatePos(gritCharMap);

            if(mapaTest1.allPointsCollected(pointA)){
                System.out.println("//====DONE====//");
                wszystkiePonkty+=hero.getPonkty();
                hero.setPonkty(0);
            };
        }
    }
}
