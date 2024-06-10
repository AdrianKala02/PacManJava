package GuiSides;

import objectsForGame.Blok;
import objectsForGame.Enemy;
import objectsForGame.Hero;
import objectsForGame.PointToCollect;
import toolBox.ANIAMTIONTYPE;
import toolBox.AnimateHandler;
import toolBox.ColorRGB;
import toolBox.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class Rozgrywka extends JPanel implements Runnable {

    private final long fps = 1000 / 144;
    private int wszystkiePonkty;
    private boolean przegrana;
    public boolean isPrzegrana() {return przegrana;}

    public void stopIt(){przegrana=true;}

    boolean rezygnacjaZWyboru;
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
        rezygnacjaZWyboru=false;
        przegrana = false;
        setBackground(new Color(98, 158, 225));
        setFocusable(true);
        hero = new Hero("./PacManAsets/PacMan/SpriteSheet-PacMan3.png", new ColorRGB(0, 255, 0), 'H');
        enemy= new Enemy("./PacManAsets/Ghost/SpriteSheet-Ghost2.png",new ColorRGB(255,0,0),'E');
        blokA = new Blok("./PacManAsets/Wall/wall.png", new ColorRGB(0, 0, 0), 'B');
        pointA=new PointToCollect(1,"./PacManAsets/Other/Point.png",new ColorRGB(0,0,255),'P');
        //AnimateHandler wykorzystuje już w sobie nowy wątek
        heroAnimateHandler = new AnimateHandler(hero.spriteSheet, hero, 150, ANIAMTIONTYPE.ANIMATIONPINGPONG);
        mapaTest1 = new Map(mapUrl, blokA, hero,pointA,enemy);
        mapaTest1.inicjal(this);
        Thread th=new Thread(mapaTest1);
        th.start();
        enemyGang=mapaTest1.allEnemy;
        setSize(600,600);
        for(Enemy enemy1:enemyGang) {
            new AnimateHandler(enemy1.spriteSheet,enemy1,100,ANIAMTIONTYPE.ANIMATIONPINGPONG);
            Thread enemyMove = new Thread(enemy1);
            enemyMove.start();
        }
        Thread checkRestartMap=new Thread(()-> {
            while (!przegrana){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(mapaTest1.allPointsCollected()){
                System.out.println("//====DONE====//");
                wszystkiePonkty+=hero.getPonkty();
                hero.setPonkty(0);
                removeAll();
                revalidate();
                hero.setAclelerationY(0);
                hero.setAclelerationX(0);
                mapaTest1.inicjalV2(this);
                mapaTest1.turnOffAllPowers();
            }
        }});
        checkRestartMap.start();

        Thread heroPos=new Thread(()-> {
            while (!przegrana) {
                try {
                    Thread.sleep(hero.getSpeed());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                mapaTest1.updatePos();
            }
        });
        heroPos.start();
        Thread enemyPos=new Thread(()->{
            while (!przegrana){
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                mapaTest1.updatePosE();
            }
        });
        enemyPos.start();

        Thread colisonEv=new Thread(()->{
            while (!przegrana){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                mapaTest1.colisionEvade();
            }
        });
        colisonEv.start();

        mapaTest1.powerBoostScale(getHeight(),getWidth());
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                mapaTest1.reSize(getSize().height,getSize().width);
            }
        });
        setLayout(new GridLayout(mapaTest1.getGritCharMap().length,mapaTest1.getGritCharMap()[0].length));
    }

    @Override
    public void run() {
        while (!przegrana) {
            //!//  System.out.println(Thread.currentThread()+" "+getClass().getName());
            if(getHeroHP()<=0){przegrana=true;}
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            for (int y = 0; y < mapaTest1.getGritCharMap().length; y++) {
//                for (int x = 0; x < mapaTest1.getGritCharMap()[0].length; x++) {
//                    System.out.print(mapaTest1.getGritCharMap()[y][x] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("//+======================+//");
        }
        mapaTest1.stopIt();
        for(Enemy enemy:mapaTest1.allEnemy){
            enemy.stopIt();
        }
    }
}
