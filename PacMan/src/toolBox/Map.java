package toolBox;

import MyGui.MyJlabel;
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
                        public int getHeightRozgrywka() {
                            return heightRozgrywka;
                        }

                        public void setHeightRozgrywka(int heightRozgrywka) {
                            this.heightRozgrywka = heightRozgrywka;
                        }

                        public int getWidthRozgrywka() {
                            return widthRozgrywka;
                        }

                        public void setWidthRozgrywka(int widthRozgrywka) {
                            this.widthRozgrywka = widthRozgrywka;
                        }

                        public BufferedImage getMapaPng() {
                            return mapaPng;
                        }

                        public void setMapaPng(BufferedImage mapaPng) {
                            this.mapaPng = mapaPng;
                        }

                        public boolean isNotFreezed() {
                            return notFreezed;
                        }

                        public void setNotFreezed(boolean notFreezed) {
                            this.notFreezed = notFreezed;
                        }

                        public boolean isAlive() {
                            return alive;
                        }

                        public void setAlive(boolean alive) {
                            this.alive = alive;
                        }

                        public boolean ishToE() {
                            return hToE;
                        }

                        public void sethToE(boolean hToE) {
                            this.hToE = hToE;
                        }

                        public boolean iseToH() {
                            return eToH;
                        }

                        public void seteToH(boolean eToH) {
                            this.eToH = eToH;
                        }

                        public Blok getBlokA() {
                            return blokA;
                        }

                        public void setBlokA(Blok blokA) {
                            this.blokA = blokA;
                        }

                        public Hero getHero() {
                            return hero;
                        }

                        public void setHero(Hero hero) {
                            this.hero = hero;
                        }

                        public PointToCollect getPointA() {
                            return pointA;
                        }

                        public void setPointA(PointToCollect pointA) {
                            this.pointA = pointA;
                        }

                        public ArrayList<Enemy> getAllEnemy() {
                            return allEnemy;
                        }

                        public void setAllEnemy(ArrayList<Enemy> allEnemy) {
                            this.allEnemy = allEnemy;
                        }

                        public Enemy getEn() {
                            return en;
                        }

                        public void setEn(Enemy en) {
                            this.en = en;
                        }

                        public ObjCreator getFreezBOOST() {
                            return freezBOOST;
                        }

                        public void setFreezBOOST(ObjCreator freezBOOST) {
                            this.freezBOOST = freezBOOST;
                        }

                        public ObjCreator getGoHomeBOOST() {
                            return goHomeBOOST;
                        }

                        public void setGoHomeBOOST(ObjCreator goHomeBOOST) {
                            this.goHomeBOOST = goHomeBOOST;
                        }

                        public ObjCreator getShiledBOOST() {
                            return shiledBOOST;
                        }

                        public void setShiledBOOST(ObjCreator shiledBOOST) {
                            this.shiledBOOST = shiledBOOST;
                        }

                        public ObjCreator getSlowThinkBOOST() {
                            return slowThinkBOOST;
                        }

                        public void setSlowThinkBOOST(ObjCreator slowThinkBOOST) {
                            this.slowThinkBOOST = slowThinkBOOST;
                        }

                        public ObjCreator getSpeedsterBOOST() {
                            return speedsterBOOST;
                        }

                        public void setSpeedsterBOOST(ObjCreator speedsterBOOST) {
                            this.speedsterBOOST = speedsterBOOST;
                        }

                        public SuperPower getSuperPower() {
                            return superPower;
                        }

                        public void setSuperPower(SuperPower superPower) {
                            this.superPower = superPower;
                        }

                        public String getRodzajRozgrywki() {
                            return rodzajRozgrywki;
                        }

                        public void setRodzajRozgrywki(String rodzajRozgrywki) {
                            this.rodzajRozgrywki = rodzajRozgrywki;
                        }

                        private int heightRozgrywka;
   private int widthRozgrywka;
   private BufferedImage mapaPng;
   volatile private MyJlabel[][] gritGame;
    public MyJlabel[][] getGritGame() { return gritGame; }
    public void setGritGame(MyJlabel[][] gritGame) { this.gritGame = gritGame; }
     private boolean notFreezed;
    volatile private Character[][] gritCharMap;
    public Character[][] getGritCharMap() { return gritCharMap; }
    public void setGritCharMap(Character[][] gritCharMap) { this.gritCharMap = gritCharMap; }
    private boolean alive;
                        private boolean hToE;
                        private boolean eToH;
                        private Blok blokA;
                        private Hero hero;
                        private PointToCollect pointA;
                        private ArrayList<Enemy> allEnemy;
                        private Enemy en;

                        private ObjCreator freezBOOST;
                        private ObjCreator goHomeBOOST;
                        private ObjCreator shiledBOOST;
                        private ObjCreator slowThinkBOOST;
                        private ObjCreator speedsterBOOST;
                        private SuperPower superPower;

                        private String rodzajRozgrywki;

    public void stopIt(){alive=false;}
    public Map(String url,String rodzajRozgrywki, Blok blokA, Hero hero, PointToCollect pointA, Enemy en) {
        this.rodzajRozgrywki=rodzajRozgrywki;
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
        gritGame = new MyJlabel[gritCharMap.length][gritCharMap[0].length];



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
            if(enemy.isUnder()){
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
                MyJlabel label = new MyJlabel();
                if (gritCharMap[y][x] == hero.getIdChar()) {
                    hero.setStartPosX(x);
                    hero.setStartPosY(y);
                    hero.setPosX(hero.getStartPosX());
                    hero.setPosY(hero.getStartPosY());
                    label.setIcon(hero.getImageIcon());
                } else if (gritCharMap[y][x] == blokA.getIdChar()) {
                    label.setIcon(blokA.getImageIcon());
                } else if (gritCharMap[y][x] == pointA.getIdChar()) {
                    label.setIcon(pointA.getImageIcon());
                } else if (gritCharMap[y][x] == en.getIdChar()) {
                    Enemy enK= new Enemy(en);
                    enK.setPosX(x);
                    enK.setPosY(y);
                    enK.setStartPosX(x);
                    enK.setStartPosY(y);
                    label.setIcon(enK.getImageIcon());
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
                if (hero.getMapIdColor().compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = hero.getIdChar();
                } else if (blokA.getMapIdColor().compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = blokA.getIdChar();
                } else if (pointA.getMapIdColor().compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = pointA.getIdChar();
                } else if (en.getMapIdColor().compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
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
        freezBOOST.setImageIcon(new ImageIcon(freezBOOST.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));
        slowThinkBOOST.setImageIcon(new ImageIcon(slowThinkBOOST.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));
        goHomeBOOST.setImageIcon(new ImageIcon(goHomeBOOST.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));
        shiledBOOST.setImageIcon(new ImageIcon(shiledBOOST.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));
        speedsterBOOST.setImageIcon(new ImageIcon(speedsterBOOST.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));

    }
    public void inicjalV2(JPanel rozgrywka) {
        GridBagConstraints gbc = new GridBagConstraints();
        loadPngToCharMap();
        for (int y = 0; y < gritCharMap.length; y++) {
            for (int x = 0; x < gritCharMap[0].length; x++) {
                MyJlabel label = new MyJlabel();
                if (gritCharMap[y][x] == hero.getIdChar()) {
                    hero.setPosX(hero.getStartPosX());
                    hero.setPosY(hero.getStartPosY());
                    label.setIcon(hero.getImageIcon());
                } else if (gritCharMap[y][x] == blokA.getIdChar()) {
                    label.setIcon(blokA.getImageIcon());
                } else if (gritCharMap[y][x] == pointA.getIdChar()) {
                    label.setIcon(pointA.getImageIcon());
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
                hero.addPonkty(pointA.getValueOfPoint());
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
                    if (enemy.isUnder()) {
                        gritCharMap[enemy.getPosY()][enemy.getPosX()] = enemy.getCharUnder();
                    } else {
                        gritCharMap[enemy.getPosY()][enemy.getPosX()] = 'X';
                    }
                    if (gritCharMap[newY][newX] == 'P') {
                        enemy.setCharUnder('P');
                        enemy.setUnder(true);
                    } else if (gritCharMap[newY][newX] == 'F') {
                        enemy.setCharUnder('F');
                        enemy.setUnder(true);
                    } else if (gritCharMap[newY][newX] == 'A') {
                        enemy.setCharUnder('A');
                        enemy.setUnder(true);
                    } else if (gritCharMap[newY][newX] == 'S') {
                        enemy.setCharUnder('S');
                        enemy.setUnder(true);
                    } else if (gritCharMap[newY][newX] == 'Q') {
                        enemy.setCharUnder('Q');
                        enemy.setUnder(true);
                    } else if (gritCharMap[newY][newX] == 'T') {
                        enemy.setCharUnder('T');
                        enemy.setUnder(true);
                    }
                    else {
                        enemy.setUnder(false);
                    }
                    enemy.setPosX(newX);
                    enemy.setPosY(newY);
                    gritCharMap[newY][newX] = 'E';
                } else if (gritCharMap[newY][newX] == 'H') {
                    eToH=true;
                }
                if(enemy.isDropThatBomb()&&!enemy.isUnder()&&gritCharMap[enemy.getOldPosY()][enemy.getOldPosX()]=='X'){
                    gritCharMap[enemy.getOldPosY()][enemy.getOldPosX()]=enemy.getGift();
                    enemy.setDropThatBomb(false);
                }
            }
        }
        }

    public void colisionEvade() {
        if (eToH || hToE) {


            //wybor w jaki sposób chcemy grać. Czy: one hit teleport to start position, more hp but to reset position
            if(rodzajRozgrywki.equals("normal")){
                if (!hero.isCoverToDmg()) {
                    hero.addZycia(-1);

                    gritCharMap[hero.getPosY()][hero.getPosX()]='X';

                    hero.setPosX(hero.getStartPosX());
                    hero.setPosY(hero.getStartPosY());

                    gritCharMap[hero.getStartPosY()][hero.getStartPosX()]='H';
                    hero.setAclelerationY(0);
                    hero.setAclelerationX(0);

                    superPower.goHome(allEnemy,gritCharMap);

                    eToH = false;
                    hToE = false;
                }
            }else {
                flipPos();
                if (!hero.isCoverToDmg()) {
                    hero.addZycia(-1);
                }
                eToH = false;
                hToE = false;
            }
        }
    }

    public void flipPos() {
        for (Enemy enemy : allEnemy) {
            int tmpX = hero.getPosX();
            int tmpY = hero.getPosY();

            if(hero.getPosX()+hero.getAclelerationX()==enemy.getPosX()&&hero.getPosY()+hero.getAclelerationY()==enemy.getPosY()) {
                hero.setPosX(enemy.getPosX());
                hero.setPosY(enemy.getPosY());
                gritCharMap[enemy.getPosY()][enemy.getPosX()] = 'H';

                enemy.setPosX(tmpX);
                enemy.setPosY(tmpY);
                gritCharMap[tmpY][tmpX] = 'E';
            }
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
                    hero.setImageIcon(new ImageIcon(hero.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));
                } else if (gritCharMap[y][x] == blokA.getIdChar()) {
                    blokA.setImageIcon(new ImageIcon(blokA.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));
                } else if (gritCharMap[y][x] == pointA.getIdChar()) {
                    pointA.setImageIcon(new ImageIcon(pointA.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));
                } else if (gritCharMap[y][x] == en.getIdChar()){
                    for (Enemy enemy : allEnemy) {
                        if(y==enemy.getPosY()&&x==enemy.getPosX()) {
                            enemy.setScaledX(cellWidth);
                            enemy.setScaledY(cellHeight);
                            enemy.setImageIcon(new ImageIcon(enemy.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));
                        }
                    }
                    //BOOSTERS
                    //F - freez
                    //A - slowThink
                    //T - goHome
                    //S - shield
                    //Q - speedster
                }else if (gritCharMap[y][x] == 'F') {
                    freezBOOST.setImageIcon(new ImageIcon(freezBOOST.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));
                }else if (gritCharMap[y][x] == 'A') {
                    slowThinkBOOST.setImageIcon(new ImageIcon(slowThinkBOOST.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));
                }else if (gritCharMap[y][x] == 'T') {
                    goHomeBOOST.setImageIcon(new ImageIcon(goHomeBOOST.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));
                }else if (gritCharMap[y][x] == 'S') {
                    shiledBOOST.setImageIcon(new ImageIcon(shiledBOOST.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));
                }else if (gritCharMap[y][x] == 'Q') {
                    speedsterBOOST.setImageIcon(new ImageIcon(speedsterBOOST.getSprite().getScaledInstance(cellWidth,cellHeight, Image.SCALE_SMOOTH)));
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
                        gritGame[y][x].setIcon(hero.getImageIcon());
                    } else if (gritCharMap[y][x] == blokA.getIdChar()) {
                        gritGame[y][x].setIcon(blokA.getImageIcon());
                    } else if (gritCharMap[y][x] == pointA.getIdChar()) {
                        gritGame[y][x].setIcon(pointA.getImageIcon());
                    } else if (gritCharMap[y][x] == en.getIdChar()){
                        for (Enemy enemy : allEnemy) {
                            if(y==enemy.getPosY()&&x==enemy.getPosX()) {
                                gritGame[y][x].setIcon(enemy.getImageIcon());

                            }
                        }
                        //BOOSTERS
                        //F - freez
                        //A - slowThink
                        //T - goHome
                        //S - shield
                        //Q - speedster
                    }else if (gritCharMap[y][x] == 'F') {
                        gritGame[y][x].setIcon(freezBOOST.getImageIcon());
                    }else if (gritCharMap[y][x] == 'A') {
                        gritGame[y][x].setIcon(slowThinkBOOST.getImageIcon());
                    }else if (gritCharMap[y][x] == 'T') {
                        gritGame[y][x].setIcon(goHomeBOOST.getImageIcon());
                    }else if (gritCharMap[y][x] == 'S') {
                        gritGame[y][x].setIcon(shiledBOOST.getImageIcon());
                    }else if (gritCharMap[y][x] == 'Q') {
                        gritGame[y][x].setIcon(speedsterBOOST.getImageIcon());
                    }
                    else {
                        gritGame[y][x].setIcon(null);
                    }
                }
            }
        });
    }


                    public void turnOffAllPowers(){
                        superPower.offAll(this,allEnemy,hero);
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
