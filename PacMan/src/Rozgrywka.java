import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Rozgrywka extends JPanel implements Runnable {

    private final long fps = 1000 / 144;
    private int wszystkiePonkty;
    private boolean przegrana;
    public boolean isPrzegrana() {return przegrana;}

    public void setPrzegrana(){przegrana=false;}

    Hero hero;
    ArrayList<Enemy> enemyGang;
    Enemy enemy;
    Blok blokA;
    PointToCollect pointA;
    Map mapaTest1;
    public int getWszystkiePonkty() {return wszystkiePonkty+hero.getPonkty();}
    public void setWszystkiePonkty(int wszystkiePonkty) {this.wszystkiePonkty = wszystkiePonkty;}
    public int getHeroPoints(){return hero.getPonkty();}
    public int getHeroHP(){return hero.getZycia();}
    AnimateHandler heroAnimateHandler;

    Rozgrywka(String mapUrl) {
        przegrana = false;
        setBackground(new Color(98, 158, 225));
        setFocusable(true);
        setLayout(new GridBagLayout());
        hero = new Hero("./PacManAsets/PacMan/SpriteSheet-PacMan3.png", new ColorRGB(0, 255, 0), 'H');
        enemy= new Enemy("./PacManAsets/Ghost/SpriteSheet-Ghost2.png",new ColorRGB(255,0,0),'E');
        blokA = new Blok("./PacManAsets/Wall/wall.png", new ColorRGB(0, 0, 0), 'B');
        pointA=new PointToCollect(1,"./PacManAsets/Other/Point.png",new ColorRGB(0,0,255),'P');
        //AnimateHandler wykorzystuje już w sobie nowy wątek
        heroAnimateHandler = new AnimateHandler(hero.spriteSheet, hero, 200, ANIAMTIONTYPE.ANIMATIONPINGPONG);
       // AnimateHandler enemyAnimateHandler=new AnimateHandler(enemy.spriteSheet,enemy,100,ANIAMTIONTYPE.ANIMATIONPINGPONG);

        mapaTest1 = new Map(mapUrl, blokA, hero,pointA,enemy);
        mapaTest1.inicjal(this);
//        Thread th=new Thread(mapaTest1);
//        th.start();
        enemyGang=mapaTest1.allEnemy;
        for(Enemy enemy1:enemyGang) {
            new AnimateHandler(enemy1.spriteSheet,enemy1,100,ANIAMTIONTYPE.ANIMATIONPINGPONG);
            Thread enemyMove = new Thread(enemy1);
            enemyMove.start();
        }

    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread() + " " + getClass().getName());
        while (!przegrana) {
            if(getHeroHP()<=0){przegrana=true;}
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mapaTest1.updatePos();
            mapaTest1.updatePosE();
            mapaTest1.colisionEvade();
            mapaTest1.refresh();

            for (int y = 0; y < mapaTest1.getGritCharMap().length; y++) {
                for (int x = 0; x < mapaTest1.getGritCharMap()[0].length; x++) {
                    System.out.print(mapaTest1.getGritCharMap()[y][x] + " ");
                }
                System.out.println();
            }
            System.out.println("//+======================+//");
            if(mapaTest1.allPointsCollected(pointA)){
                System.out.println("//====DONE====//");
                wszystkiePonkty+=hero.getPonkty();
                hero.setPonkty(0);
                removeAll();
                revalidate();
                hero.setAclelerationY(0);
                hero.setAclelerationX(0);
                mapaTest1.inicjal(this);

            };
        }
        hero.stopIt();

    }
}
