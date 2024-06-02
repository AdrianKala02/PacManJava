import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Map implements Runnable {
    BufferedImage mapaPng;
    private MyJlable[][] gritGame;
    public MyJlable[][] getGritGame() { return gritGame; }
    public void setGritGame(MyJlable[][] gritGame) { this.gritGame = gritGame; }

    private Character[][] gritCharMap;
    public Character[][] getGritCharMap() { return gritCharMap; }
    public void setGritCharMap(Character[][] gritCharMap) { this.gritCharMap = gritCharMap; }

    Blok blokA;
    Hero hero;
    PointToCollect pointA;
    Enemy enemyA;

    Map(String url, Blok blokA, Hero hero, PointToCollect pointA, Enemy enemyA) {
        this.blokA = blokA;
        this.hero = hero;
        this.pointA = pointA;
        this.enemyA = enemyA;
        try {
            mapaPng = ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gritCharMap = new Character[mapaPng.getHeight()][mapaPng.getWidth()]; // Zamienione wymiary
        gritGame = new MyJlable[gritCharMap.length][gritCharMap[0].length];

        for (int y = 0; y < mapaPng.getHeight(); y++) {
            for (int x = 0; x < mapaPng.getWidth(); x++) {
                gritCharMap[y][x] = 'X'; // Zamienione współrzędne
                if (hero.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = hero.getIdChar();
                } else if (blokA.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = blokA.getIdChar();
                } else if (pointA.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = pointA.getIdChar();
                } else if (enemyA.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = enemyA.getIdChar();
                }
            }
        }
    }

    public boolean allPointsCollected(PointToCollect pointA) {
        for (int y = 0; y < gritCharMap.length; y++) {
            for (int x = 0; x < gritCharMap[0].length; x++) {
                if (pointA.getIdChar() == gritCharMap[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void inicjal(JPanel rozgrywka) {
        GridBagConstraints gbc = new GridBagConstraints();

        for (int y = 0; y < mapaPng.getHeight(); y++) {
            for (int x = 0; x < mapaPng.getWidth(); x++) {
                gritCharMap[y][x] = 'X';
                if (hero.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = hero.getIdChar();
                } else if (blokA.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = blokA.getIdChar();
                } else if (pointA.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = pointA.getIdChar();
                } else if (enemyA.mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x, y)))) {
                    gritCharMap[y][x] = enemyA.getIdChar();
                }
            }
        }
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
                } else if (gritCharMap[y][x] == enemyA.getIdChar()) {
                    enemyA.setPosX(x); // Zamienione współrzędne
                    enemyA.setPosY(y);
                    label.setIcon(enemyA.imageIcon);
                }
                gritGame[y][x] = label;
                gbc.gridx = x;
                gbc.gridy = y;
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
            if (gritCharMap[newY][newX] == 'E') {
                hero.addZycia(-1);
                gritCharMap[oldY][oldX] = 'E';
            } else if (gritCharMap[newY][newX] == 'P') {
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

    public void updatePosE() {
        int newX = enemyA.getPosX() + enemyA.getAclelerationX();
        int newY = enemyA.getPosY() + enemyA.getAclelerationY();

        // Sprawdzenie, czy nowa pozycja nie jest blokadą ('B')
        if (gritCharMap[newY][newX] != 'B') {

            // Aktualizacja poprzedniej pozycji przeciwnika na mapie
            if (enemyA.isUnder) {
                gritCharMap[enemyA.getPosY()][enemyA.getPosX()] = enemyA.charUnder;
            } else {
                gritCharMap[enemyA.getPosY()][enemyA.getPosX()] = 'X';
            }

            // Jeśli przeciwnik porusza się przez punkt ('P')
            if (gritCharMap[newY][newX] == 'P') {
                enemyA.charUnder = 'P';
                enemyA.isUnder = true;
            } else {
                enemyA.isUnder = false;
            }

            // Przeniesienie przeciwnika na nową pozycję
            enemyA.setPosX(newX);
            enemyA.setPosY(newY);
            gritCharMap[newY][newX] = 'E';
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
                for (int y = 0; y < gritGame.length; y++) {
                    for (int x = 0; x < gritGame[0].length; x++) {
                        if (gritCharMap[y][x] == hero.getIdChar()) {
                            gritGame[y][x].setIcon(hero.imageIcon);
                        } else if (gritCharMap[y][x] == blokA.getIdChar()) {
                            gritGame[y][x].setIcon(blokA.imageIcon);
                        } else if (gritCharMap[y][x] == pointA.getIdChar()) {
                            gritGame[y][x].setIcon(pointA.imageIcon);
                        } else if (gritCharMap[y][x] == enemyA.getIdChar()) {
                            gritGame[y][x].setIcon(enemyA.imageIcon);
                        } else {
                            gritGame[y][x].setIcon(null);
                        }
                    }
                }
            });
        }
    }
}
