package toolBox;

import GuiSides.Rozgrywka;
import MyGui.MyJFrame;
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


    public int heightRozgrywka;
    public int widthRozgrywka;
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

    ObjCreator freezBOOST;
    ObjCreator goHomeBOOST;
    ObjCreator shiledBOOST;
    ObjCreator slowThinkBOOST;
    ObjCreator speedsterBOOST;
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



        freezBOOST=new ObjCreator("./PacManAsets/Boost/freazer-BOOST.png",new ColorRGB(1,1,1),'F');
        goHomeBOOST=new ObjCreator("./PacManAsets/Boost/goHome-BOOST.png",new ColorRGB(1,1,1),'T');
        shiledBOOST=new ObjCreator("./PacManAsets/Boost/shield-BOOST.png",new ColorRGB(1,1,1),'S');
        slowThinkBOOST=new ObjCreator("./PacManAsets/Boost/slowThink-BOOST.png",new ColorRGB(1,1,1),'A');
        speedsterBOOST=new ObjCreator("./PacManAsets/Boost/speedster-BOOST.png",new ColorRGB(1,1,1),'Q');



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

    public void powerBoostScale(int heightRozgrywka,int widthRozgrywka){
        this.widthRozgrywka=widthRozgrywka;
        this.heightRozgrywka=heightRozgrywka;

        int cellWidth = widthRozgrywka / gritGame[0].length;
        int cellHeight = heightRozgrywka / gritGame.length;
        freezBOOST.imageIcon=new ImageIcon(freezBOOST.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));
        slowThinkBOOST.imageIcon=new ImageIcon(slowThinkBOOST.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));
        goHomeBOOST.imageIcon=new ImageIcon(goHomeBOOST.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));
        shiledBOOST.imageIcon=new ImageIcon(shiledBOOST.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));
        speedsterBOOST.imageIcon=new ImageIcon(speedsterBOOST.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));

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
                superPower.goHome(allEnemy,gritCharMap);
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
                hToE=true;
            }
            hero.setPosX(oldX);
            hero.setPosY(oldY);
        }
    }

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
                    } else if (gritCharMap[newY][newX] == 'F') {
                        enemy.charUnder = 'F';
                        enemy.isUnder = true;
                    } else if (gritCharMap[newY][newX] == 'A') {
                        enemy.charUnder = 'A';
                        enemy.isUnder = true;
                    } else if (gritCharMap[newY][newX] == 'S') {
                        enemy.charUnder = 'S';
                        enemy.isUnder = true;
                    } else if (gritCharMap[newY][newX] == 'Q') {
                        enemy.charUnder = 'Q';
                        enemy.isUnder = true;
                    } else if (gritCharMap[newY][newX] == 'T') {
                        enemy.charUnder = 'T';
                        enemy.isUnder = true;
                    }
                    else {
                        enemy.isUnder = false;
                    }
                    enemy.setPosX(newX);
                    enemy.setPosY(newY);
                    gritCharMap[newY][newX] = 'E';
                } else if (gritCharMap[newY][newX] == 'H') {
                    eToH=true;
                }
                if(enemy.dropThatBomb&&!enemy.isUnder&&gritCharMap[enemy.getOldPosY()][enemy.getOldPosX()]=='X'){
                    gritCharMap[enemy.getOldPosY()][enemy.getOldPosX()]=enemy.getGift();
                    enemy.dropThatBomb=false;
                }
            }
        }
        }

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

    public void reSize(int height,int width){
        int cellWidth = width / gritGame[0].length;
        int cellHeight = height / gritGame.length;
        for (int y = 0; y < gritGame.length; y++) {
            for (int x = 0; x < gritGame[0].length; x++) {
                if (gritCharMap[y][x] == hero.getIdChar()) {
                    hero.setScaledX(cellWidth);
                    hero.setScaledY(cellHeight);
                    hero.imageIcon=new ImageIcon(hero.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));
                } else if (gritCharMap[y][x] == blokA.getIdChar()) {
                    blokA.imageIcon=new ImageIcon(blokA.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));
                } else if (gritCharMap[y][x] == pointA.getIdChar()) {
                    pointA.imageIcon=new ImageIcon(pointA.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));
                } else if (gritCharMap[y][x] == en.getIdChar()){
                    for (Enemy enemy : allEnemy) {
                        if(y==enemy.getPosY()&&x==enemy.getPosX()) {
                            enemy.setScaledX(cellWidth);
                            enemy.setScaledY(cellHeight);
                            enemy.imageIcon=new ImageIcon(enemy.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));
                        }
                    }
                    //BOOSTERS
                    //F - freez
                    //A - slowThink
                    //T - goHome
                    //S - shield
                    //Q - speedster
                }else if (gritCharMap[y][x] == 'F') {
                    freezBOOST.imageIcon=new ImageIcon(freezBOOST.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));
                }else if (gritCharMap[y][x] == 'A') {
                    slowThinkBOOST.imageIcon=new ImageIcon(slowThinkBOOST.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));
                }else if (gritCharMap[y][x] == 'T') {
                    goHomeBOOST.imageIcon=new ImageIcon(goHomeBOOST.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));
                }else if (gritCharMap[y][x] == 'S') {
                    shiledBOOST.imageIcon=new ImageIcon(shiledBOOST.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));
                }else if (gritCharMap[y][x] == 'Q') {
                    speedsterBOOST.imageIcon=new ImageIcon(speedsterBOOST.sprite.getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH));
                }


                else {
                    gritGame[y][x].setIcon(null);
                }
            }
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
                        gritGame[y][x].setIcon(freezBOOST.imageIcon);
                    }else if (gritCharMap[y][x] == 'A') {
                        gritGame[y][x].setIcon(slowThinkBOOST.imageIcon);
                    }else if (gritCharMap[y][x] == 'T') {
                        gritGame[y][x].setIcon(goHomeBOOST.imageIcon);
                    }else if (gritCharMap[y][x] == 'S') {
                        gritGame[y][x].setIcon(shiledBOOST.imageIcon);
                    }else if (gritCharMap[y][x] == 'Q') {
                        gritGame[y][x].setIcon(speedsterBOOST.imageIcon);
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
