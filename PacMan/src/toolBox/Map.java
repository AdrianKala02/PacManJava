package toolBox;

import MyGui.MyJlable;
import objectsForGame.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



                    //GRIT CHAR MAP LEGEND
                        //H - hero/pacman
                        //E - enemy/ghost
                        //P - point
                        //B - blok/wall


                    //BOOSTERS
                        //F - freez
                        //A - slowThink
                        //T - goHome
                        //S - shield
                        //Q - speedster
public class Map implements Runnable {
    BufferedImage mapaPng;
   volatile private MyJlable[][] gritGame;
    public MyJlable[][] getGritGame() { return gritGame; }
    public void setGritGame(MyJlable[][] gritGame) { this.gritGame = gritGame; }

    public boolean notFreezed;
    volatile private Character[][] gritCharMap;
    public Character[][] getGritCharMap() { return gritCharMap; }
    public void setGritCharMap(Character[][] gritCharMap) { this.gritCharMap = gritCharMap; }
    private boolean alive;
    boolean hToE;
    boolean eToH;
    Blok blokA;
    Hero hero;
    PointToCollect pointA;
    public ArrayList<Enemy> allEnemy;
    Enemy en;

    SuperPower superPower;

    public void stopIt(){alive=false;}
    public Map(String url, Blok blokA, Hero hero, PointToCollect pointA, Enemy en) {
        superPower=new SuperPower();
        alive=true;
        eToH=false;
        hToE=false;
        this.blokA = blokA;
        this.hero = hero;
        this.pointA = pointA;
        allEnemy = new ArrayList<>();
        notFreezed=true;
        this.en=en;
        try {
            mapaPng = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gritCharMap = new Character[mapaPng.getHeight()][mapaPng.getWidth()]; // Zamienione wymiary
        gritGame = new MyJlable[gritCharMap.length][gritCharMap[0].length];

        loadPngToCharMap();
    }

    public boolean allPointsCollected() {
        for (int y = 0; y < gritCharMap.length; y++) {
            for (int x = 0; x < gritCharMap[0].length; x++) {
                if (pointA.getIdChar() == gritCharMap[y][x]) {
                    return false;
                }
            }
        }
        for(Enemy enemy:allEnemy){
            if(enemy.isUnder){
                return false;
            }
        }
        return true;
    }

    public void inicjal(JPanel rozgrywka) {
        GridBagConstraints gbc = new GridBagConstraints();

        loadPngToCharMap();
        for (int y = 0; y < gritCharMap.length; y++) {
            for (int x = 0; x < gritCharMap[0].length; x++) {
                MyJlable label = new MyJlable();
                if (gritCharMap[y][x] == hero.getIdChar()) {
                    hero.setPosX(x); // Zamienione współrzędne
                    hero.setPosY(y);
                    label.setIcon(hero.imageIcon);
                } else if (gritCharMap[y][x] == blokA.getIdChar()) {
                    label.setIcon(blokA.imageIcon);
                } else if (gritCharMap[y][x] == pointA.getIdChar()) {
                    label.setIcon(pointA.imageIcon);
                } else if (gritCharMap[y][x] == en.getIdChar()) {
                    Enemy enK= new Enemy(en);
                    enK.setPosX(x); // Zamienione współrzędne
                    enK.setPosY(y);
                    enK.setStartPosX(x);
                    enK.setStartPosY(y);
                    label.setIcon(enK.imageIcon);
                    allEnemy.add(enK);
                }

                gritGame[y][x] = label;
                gbc.gridx = x;
                gbc.gridy = y;
                rozgrywka.add(label, gbc);
            }
        }
    }
    public void loadPngToCharMap(){
        for (int y = 0; y < mapaPng.getHeight(); y++) {
            for (int x = 0; x < mapaPng.getWidth(); x++) {
                gritCharMap[y][x] = 'X';
                if (hero.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = hero.getIdChar();
                } else if (blokA.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = blokA.getIdChar();
                } else if (pointA.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = pointA.getIdChar();
                } else if (en.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = en.getIdChar();
                }
            }
        }
    }

    public void inicjalV2(JPanel rozgrywka) {
        GridBagConstraints gbc = new GridBagConstraints();
        loadPngToCharMap();
        for (int y = 0; y < gritCharMap.length; y++) {
            for (int x = 0; x < gritCharMap[0].length; x++) {
                MyJlable label = new MyJlable();
                if (gritCharMap[y][x] == hero.getIdChar()) {
                    hero.setPosX(x); // Zamienione współrzędne
                    hero.setPosY(y);
                    label.setIcon(hero.imageIcon);
                } else if (gritCharMap[y][x] == blokA.getIdChar()) {
                    label.setIcon(blokA.imageIcon);
                } else if (gritCharMap[y][x] == pointA.getIdChar()) {
                    label.setIcon(pointA.imageIcon);
                }
                for (Enemy enemy : allEnemy) {

                    enemy.setPosX(enemy.getStartPosX());
                    enemy.setPosY(enemy.getStartPosY());

                }
                gritGame[y][x] = label;
                gbc.gridx = x;
                gbc.gridy = y;
                rozgrywka.add(label, gbc);
            }
        }
    }
    //i need sleep so for now Quick deevolution where pacman and ghost see each other as blocks
    public void updatePos(){
                int oldX = hero.getPosX();
        int oldY = hero.getPosY();

        int newX = oldX + hero.getAclelerationX();
        int newY = oldY + hero.getAclelerationY();

        if (gritCharMap[newY][newX] != 'B'&&gritCharMap[newY][newX] != 'E') {
            gritCharMap[oldY][oldX] = 'X';

            if (gritCharMap[newY][newX] == 'P') {
                hero.addPonkty(1);
                gritCharMap[oldY][oldX] = 'X';
            } else if (gritCharMap[newY][newX] == 'F') {
                superPower.freazer(this);
            }else if (gritCharMap[newY][newX] == 'A') {
                superPower.slowThink(allEnemy);
            }else if (gritCharMap[newY][newX] == 'T') {
                superPower.goHome(allEnemy);
            }else if (gritCharMap[newY][newX] == 'S') {
                superPower.sheeldIt(hero);
            }else if (gritCharMap[newY][newX] == 'Q') {
                superPower.speedster(hero);
            }
            gritCharMap[newY][newX]=hero.getIdChar();
            hero.setPosY(newY);
            hero.setPosX(newX);
    }else {
            if(gritCharMap[newY][newX] == 'E'){
                hero.addZycia(-1);
            }
            hero.setPosX(oldX);
            hero.setPosY(oldY);
        }
    }
//    public void updatePos() {
//        int oldX = hero.getPosX();
//        int oldY = hero.getPosY();
//
//        int newX = oldX + hero.getAclelerationX();
//        int newY = oldY + hero.getAclelerationY();
//
//        if (gritCharMap[newY][newX] != 'B'&&gritCharMap[newY][newX] != 'E') {
//            gritCharMap[oldY][oldX] = 'X';
//
//            if (gritCharMap[newY][newX] == 'P') {
//                hero.addPonkty(1);
//                gritCharMap[oldY][oldX] = 'X';
//            }
//
//            gritCharMap[newY][newX] = hero.getIdChar();
//            hero.setPosX(newX);
//            hero.setPosY(newY);
//        }else if (gritCharMap[newY][newX] == 'E') {
//                hToE = true;
//        }else {
//
//            hero.setPosX(oldX);
//            hero.setPosY(oldY);
//
//        }
//
//    }

        public void updatePosE(){
        if(notFreezed) {
            for (Enemy enemy : allEnemy) {
                enemy.setOldPosX(enemy.getPosX());
                enemy.setOldPosY(enemy.getPosY());
                int newX = enemy.getPosX() + enemy.getAclelerationX();
                int newY = enemy.getPosY() + enemy.getAclelerationY();

                if (gritCharMap[newY][newX] != 'B' && gritCharMap[newY][newX] != 'E' && gritCharMap[newY][newX] != 'H') {
                    if (enemy.isUnder) {
                        gritCharMap[enemy.getPosY()][enemy.getPosX()] = enemy.charUnder;
                    } else {
                        gritCharMap[enemy.getPosY()][enemy.getPosX()] = 'X';
                    }
                    if (gritCharMap[newY][newX] == 'P') {
                        enemy.charUnder = 'P';
                        enemy.isUnder = true;
                    } else {
                        enemy.isUnder = false;
                    }
                    enemy.setPosX(newX);
                    enemy.setPosY(newY);
                    gritCharMap[newY][newX] = 'E';
                } else if (gritCharMap[newY][newX] == 'H') {
                    hero.addZycia(-1);
                }
                if(enemy.dropThatBomb){
                    gritCharMap[enemy.getOldPosY()][enemy.getOldPosX()]=enemy.getGift();
                    enemy.dropThatBomb=false;
                }
            }
        }
        }
//    public void updatePosE() {
//        for (Enemy enemy : allEnemy) {
//            int oldX = enemy.getPosX();
//            int oldY = enemy.getPosY();
//
//            int newX = oldX + enemy.getAclelerationX();
//            int newY = oldY + enemy.getAclelerationY();
//
//            if (gritCharMap[newY][newX] != 'B' && gritCharMap[newY][newX] != 'H') {
//                if (enemy.isUnder) {
//                    gritCharMap[oldY][oldX] = enemy.charUnder;
//                } else {
//                    gritCharMap[oldY][oldX] = 'X';
//                }
//
//                if (gritCharMap[newY][newX] == 'P') {
//                    enemy.charUnder = 'P';
//                    enemy.isUnder = true;
//                } else {
//                    enemy.isUnder = false;
//                }
//
//                gritCharMap[newY][newX] = 'E';
//                enemy.setPosX(newX);
//                enemy.setPosY(newY);
//            } else if (gritCharMap[newY][newX] == 'H') {
//                eToH = true;
//            }
//        }
//    }
    public void colisionEvade() {
        if (eToH || hToE) {
            flipPos();
            if(!hero.isCoverToDmg()) {
                hero.addZycia(-1);
            }
            eToH = false;
            hToE = false;
        }
    }

    public void flipPos() {
        for (Enemy enemy : allEnemy) {
            int tmpX = hero.getPosX();
            int tmpY = hero.getPosY();

            hero.setPosX(enemy.getPosX());
            hero.setPosY(enemy.getPosY());
            gritCharMap[enemy.getPosY()][enemy.getPosX()] = 'H';

            enemy.setPosX(tmpX);
            enemy.setPosY(tmpY);
            gritCharMap[tmpY][tmpX] = 'E';
        }
    }

    public void refresh() {
        SwingUtilities.invokeLater(() -> {
            for (int y = 0; y < gritGame.length; y++) {
                for (int x = 0; x < gritGame[0].length; x++) {
                    if (gritCharMap[y][x] == hero.getIdChar()) {
                        gritGame[y][x].setIcon(hero.imageIcon);
                    } else if (gritCharMap[y][x] == blokA.getIdChar()) {
                        gritGame[y][x].setIcon(blokA.imageIcon);
                    } else if (gritCharMap[y][x] == pointA.getIdChar()) {
                        gritGame[y][x].setIcon(pointA.imageIcon);
                    } else if (gritCharMap[y][x] == en.getIdChar()){
                        for (Enemy enemy : allEnemy) {
                            if(y==enemy.getPosY()&&x==enemy.getPosX()) {
                                gritGame[y][x].setIcon(enemy.imageIcon);
                            }
                        }
                        //BOOSTERS
                        //F - freez
                        //A - slowThink
                        //T - goHome
                        //S - shield
                        //Q - speedster
                    }else if (gritCharMap[y][x] == 'F') {
                        gritGame[y][x].setIcon(new ImageIcon("./PacManAsets/Boost/freazer-BOOST.png"));
                    }else if (gritCharMap[y][x] == 'A') {
                        gritGame[y][x].setIcon(new ImageIcon("./PacManAsets/Boost/slowThink-BOOST.png"));
                    }else if (gritCharMap[y][x] == 'T') {
                        gritGame[y][x].setIcon(new ImageIcon("./PacManAsets/Boost/goHome-BOOST.png"));
                    }else if (gritCharMap[y][x] == 'S') {
                        gritGame[y][x].setIcon(new ImageIcon("./PacManAsets/Boost/shield-BOOST.png"));
                    }else if (gritCharMap[y][x] == 'Q') {
                        gritGame[y][x].setIcon(new ImageIcon("./PacManAsets/Boost/speedster-BOOST.png"));
                    }


                    else {
                        gritGame[y][x].setIcon(null);
                    }
                }
            }
        });
    }


    @Override
    public void run() {
        while (alive) {
            //!// System.out.println(Thread.currentThread()+" "+getClass().getName());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refresh();
        }
    }
}
