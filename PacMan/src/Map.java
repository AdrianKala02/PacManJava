import javax.imageio.ImageIO;
import javax.management.ListenerNotFoundException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Map implements Runnable {
    BufferedImage mapaPng;
    private MyJlable[][] gritGame;
    public MyJlable[][] getGritGame() { return gritGame; }
    public void setGritGame(MyJlable[][] gritGame) { this.gritGame = gritGame; }

    private Character[][] gritCharMap;
    public Character[][] getGritCharMap() { return gritCharMap; }
    public void setGritCharMap(Character[][] gritCharMap) { this.gritCharMap = gritCharMap; }

    boolean hToE;
    boolean eToH;
    Blok blokA;
    Hero hero;
    PointToCollect pointA;
    ArrayList<Enemy> allEnemy;
    Enemy en;

    Map(String url, Blok blokA, Hero hero, PointToCollect pointA,Enemy en) {
        eToH=false;
        hToE=false;
        this.blokA = blokA;
        this.hero = hero;
        this.pointA = pointA;
        allEnemy = new ArrayList<>();
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
            }
            gritCharMap[newY][newX]=hero.getIdChar();
            hero.setPosY(newY);
            hero.setPosX(newX);
    }else {
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
        for(Enemy enemy:allEnemy){
            int newX=enemy.getPosX()+enemy.getAclelerationX();
            int newY=enemy.getPosY()+enemy.getAclelerationY();

            if(gritCharMap[newY][newX]!='B'&&gritCharMap[newY][newX]!='E'&&gritCharMap[newY][newX]!='H'){


                if(enemy.isUnder){
                    gritCharMap[enemy.getPosY()][enemy.getPosX()]=enemy.charUnder;
                }else {
                    gritCharMap[enemy.getPosY()][enemy.getPosX()]='X';
                }
                if(gritCharMap[newY][newX]=='P'){
                    enemy.charUnder='P';
                    enemy.isUnder=true;
                }else {
                    enemy.isUnder=false;
                }
                enemy.setPosX(newX);
                enemy.setPosY(newY);
                gritCharMap[newY][newX]='E';
            }else if(gritCharMap[newY][newX]=='H'){
                hero.addZycia(-1);
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
            hero.addZycia(-1);
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
                    }else {
                        gritGame[y][x].setIcon(null);
                    }
                }
            }
        });
    }


    @Override
    public void run() {
        while (hero.isAlive()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refresh();
        }
    }
}
