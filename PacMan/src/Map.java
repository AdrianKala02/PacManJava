import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Map implements Runnable{
    BufferedImage mapaPng;
    private MyJlable[][] gritGame;
    public MyJlable[][] getGritGame() {return gritGame;}
    public void setGritGame(MyJlable[][] gritGame) {this.gritGame = gritGame;}

    private Character[][] gritCharMap;
    public Character[][] getGritCharMap() {return gritCharMap;}
    public void setGritCharMap(Character[][] gritCharMap) {this.gritCharMap = gritCharMap;}

    Blok blokA;
    Hero hero;
    PointToCollect pointA;

    Enemy enemyA;
    Map(String url, Blok blokA, Hero hero, PointToCollect pointA,Enemy enemyA){
        this.blokA=blokA;
        this.hero=hero;
        this.pointA=pointA;
        this.enemyA=enemyA;
        try {
            mapaPng = ImageIO.read(new File(url));
        }catch (IOException e){}

        gritCharMap=new Character[mapaPng.getWidth()][mapaPng.getHeight()];
        gritGame = new MyJlable[gritCharMap.length][gritCharMap[0].length];

        for(int y =0;y<mapaPng.getHeight();y++){
            for(int x=0;x<mapaPng.getWidth();x++){
                gritCharMap[x][y]='X';
                if(hero.mapIdColor.compareTo(new ColorRGB( mapaPng.getRGB(x,y)))){
                    gritCharMap[x][y]=hero.getIdChar();
                }
                else if(blokA.mapIdColor.compareTo(new ColorRGB( mapaPng.getRGB(x,y)))) {
                    gritCharMap[x][y]=blokA.getIdChar();
                }
                else if(pointA.mapIdColor.compareTo(new ColorRGB( mapaPng.getRGB(x,y)))){
                    gritCharMap[x][y]=pointA.getIdChar();
                }else if(enemyA.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x,y)))){
                    gritCharMap[x][y]=enemyA.getIdChar();
                }
            }
        }
    }

    public boolean allPointsCollected(PointToCollect pointA){
        for(int y =0;y<gritCharMap.length;y++){
            for(int x=0;x<gritCharMap[0].length;x++){
                if(pointA.getIdChar()==gritCharMap[x][y]){
                    return false;
                }
            }}
        return true;
    }

    public void inicjal(JPanel rozgrywka){
        GridBagConstraints gbc = new GridBagConstraints();

        for(int y =0;y<mapaPng.getHeight();y++){
            for(int x=0;x<mapaPng.getWidth();x++){
                gritCharMap[x][y]='X';
                if(hero.mapIdColor.compareTo(new ColorRGB( mapaPng.getRGB(x,y)))){
                    gritCharMap[x][y]=hero.getIdChar();
                }
                else if(blokA.mapIdColor.compareTo(new ColorRGB( mapaPng.getRGB(x,y)))) {
                    gritCharMap[x][y]=blokA.getIdChar();
                }
                else if(pointA.mapIdColor.compareTo(new ColorRGB( mapaPng.getRGB(x,y)))){
                    gritCharMap[x][y]=pointA.getIdChar();
                }else if(enemyA.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x,y)))){
                gritCharMap[x][y]=enemyA.getIdChar();
            }
            }
        }
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
                }else if (gritCharMap[i][j] == enemyA.getIdChar()) {
                    label.setIcon(enemyA.imageIcon);
                }
                gritGame[i][j] = label;
                gbc.gridx = j;
                gbc.gridy = i;
                rozgrywka.add(label, gbc);
            }
        }
    }


    public void updatePos() {
        int oldX = hero.getPosX();
        int oldY = hero.getPosY();

        int newX = oldX + hero.getAclelerationX();
        int newY = oldY + hero.getAclelerationY();

        if (gritCharMap[newY][newX] != 'B') {
            gritCharMap[oldY][oldX] = 'X';
            if(gritCharMap[newY][newX] == 'E'){
                hero.addZycia(-1);
                gritCharMap[oldY][oldX] = 'E';
            }
           else if (gritCharMap[newY][newX] == 'P') {
                hero.addPonkty(1);
                gritCharMap[oldY][oldX] = 'X';
            }

            gritCharMap[newY][newX] = hero.getIdChar();
            hero.setPosX(newX);
            hero.setPosY(newY);
        } else {
            hero.setPosX(oldX);
            hero.setPosY(oldY);
        }
    }

    @Override
    public void run() {
        while (hero.isAlive()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SwingUtilities.invokeLater(() -> {
                for (int i = 0; i < gritGame.length; i++) {
                    for (int j = 0; j < gritGame[0].length; j++) {
                        if (gritCharMap[i][j] == hero.getIdChar()) {
                            gritGame[i][j].setIcon(hero.imageIcon);
                        } else if (gritCharMap[i][j] == blokA.getIdChar()) {
                            gritGame[i][j].setIcon(blokA.imageIcon);
                        } else if (gritCharMap[i][j] == pointA.getIdChar()) {
                            gritGame[i][j].setIcon(pointA.imageIcon);
                        } else if (gritCharMap[i][j] == enemyA.getIdChar()) {
                            gritGame[i][j].setIcon(enemyA.imageIcon);
                        } else {
                            gritGame[i][j].setIcon(null);
                        }
                    }
                }
            });
        }
    }

}
