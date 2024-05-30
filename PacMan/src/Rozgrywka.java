import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Rozgrywka extends JPanel implements Runnable {

    private final long fps = 1000 / 144;
    private int ponkty;
    private int zycia;
    boolean przegrana;
    Character[][] gritCharMap;
    MyJlable[][] gritGame;
    Hero hero;
    Blok blokA;
    Map mapaTest1;
    ColisionHandler colisionHandler;

    public int getPonkty() { return ponkty; }
    public void setPonkty(int ponkty) { this.ponkty = ponkty; }
    public void addPonkty(int ponkty) { this.ponkty += ponkty; }

    public int getZycie() { return zycia; }
    public void setZycie(int zycia) { this.zycia = zycia; }
    public void addZycie(int zycia) { this.zycia += zycia; }

    AnimateHandler heroAnimateHandler;
    ArrayList<Blok> blokiNO1;

    Rozgrywka() {
        ponkty = 0;
        zycia = 3;
        przegrana = false;
        setBackground(new Color(98, 158, 225));
        setFocusable(true);
        setLayout(new GridBagLayout());
        hero = new Hero("/Users/adriankala/Desktop/PacManAsets/PacMan/SpriteSheet-PacMan3.png", new ColorRGB(0, 255, 0), 'H');
        blokA = new Blok("/Users/adriankala/Desktop/PacManAsets/Wall/wall.png", new ColorRGB(0, 0, 0), 'B');

        heroAnimateHandler = new AnimateHandler(hero.spriteSheet, hero, 100, ANIAMTIONTYPE.ANIMATIONPINGPONG);

        ArrayList<Blok> bloki = new ArrayList<>();
        bloki.add(blokA);

        blokiNO1 = new ArrayList<>();
        mapaTest1 = new Map("/Users/adriankala/Desktop/PacManAsets/Maps/testMap1.png", bloki, hero);

        GridBagConstraints gbc = new GridBagConstraints();

        gritCharMap = mapaTest1.mapReturn();
        colisionHandler = new ColisionHandler(hero, gritCharMap);
        for (int i = 0; i < gritCharMap.length; i++) {
            System.out.println(Arrays.toString(gritCharMap[i]));
        }



        gritGame = new MyJlable[gritCharMap.length][gritCharMap[0].length];

        for (int i = 0; i < gritCharMap.length; i++) {
            for (int j = 0; j < gritCharMap[0].length; j++) {
                MyJlable label = new MyJlable();
                if (gritCharMap[i][j] == hero.getIdChar()) {
                    hero.setPosX(i);
                    hero.setPosY(j);
                    label.setIcon(new ImageIcon(hero.sprite));
                } else if (gritCharMap[i][j] == blokA.getIdChar()) {
                    label.setIcon(new ImageIcon(blokA.sprite));
                }
                gritGame[i][j] = label;
                gbc.gridx = j;
                gbc.gridy = i;
                add(label, gbc);
            }
        }

        addKeyListener(hero);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + " " + getClass().getName());
        while (!przegrana) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            hero.updatePos(gritCharMap);

            for (int i = 0; i < gritGame.length; i++) {
                for (int j = 0; j < gritGame[0].length; j++) {
                    if (gritCharMap[i][j] == hero.getIdChar()) {
                        gritGame[i][j].setIcon(new ImageIcon(hero.sprite));
                    } else if (gritCharMap[i][j] == blokA.getIdChar()) {
                        gritGame[i][j].setIcon(new ImageIcon(blokA.sprite));
                    } else {
                        gritGame[i][j].setIcon(null);
                    }
                }
            }
        }
    }
}
