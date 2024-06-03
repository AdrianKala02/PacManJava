package objectsForGame;

import toolBox.ColorRGB;
import toolBox.DIRECTION;

import java.util.Random;

public class Enemy extends ObjCreator implements Runnable{
    private int posX;
    private int posY;
    private int aclelerationX;
    private int aclelerationY;
    private boolean alive;
     int posXUnder;
     int posYUnder;
    public boolean isUnder;
    public char charUnder;

    int posXUnderOLDER;
    int posYUnderOLDER;
    boolean isUnderOLDER;
    char charUnderOLDER;

   private int startPosX;

    public int getStartPosX() {
        return startPosX;
    }

    public void setStartPosX(int startPosX) {
        this.startPosX = startPosX;
    }

    public int getStartPosY() {
        return startPosY;
    }

    public void setStartPosY(int startPosY) {
        this.startPosY = startPosY;
    }

    private int startPosY;
    public Enemy(String url, ColorRGB mapIdColor, Character idChar) {
        super(url, mapIdColor, idChar);
        isUnder=false;
        isUnderOLDER=false;
        posX=0;
        posY=0;
        aclelerationX=0;
        aclelerationY=0;
        alive=true;
    }
    public Enemy(Enemy en){
        super(en.url,en.mapIdColor,en.getIdChar());
        isUnder=false;
        isUnderOLDER=false;
        posX=0;
        posY=0;
        aclelerationX=0;
        aclelerationY=0;
        alive=true;
    }
    public void somethingIsThere( int y,int x,char c){
        posYUnder=y;
        posXUnder=x;
        charUnder=c;
    }
        public int getPosX() {return posX;}
        public void setPosX(int posX) {this.posX = posX;}
        public int getPosY() {return posY;}
        public void setPosY(int posY) {this.posY = posY;}
        public int getAclelerationX() {return aclelerationX;}
        public void setAclelerationX(int aclelerationX) {this.aclelerationX = aclelerationX;}
        public int getAclelerationY() {return aclelerationY;}
        public void setAclelerationY(int aclelerationY) {this.aclelerationY = aclelerationY;}
        Character[][] gritCharMap;

        public boolean isAlive() {
            return alive;
        }

        public void stopIt() {alive=false;}




    public void goUp(){aclelerationY=-1;aclelerationX=0;super.direction= DIRECTION.N;directChange=true;}
    public void goDown(){aclelerationY=1;aclelerationX=0;super.direction= DIRECTION.S;directChange=true;}
    public void goLeft(){aclelerationX=-1;aclelerationY=0;super.direction= DIRECTION.W;directChange=true;}
    public void goRight(){aclelerationX=1;aclelerationY=0;super.direction= DIRECTION.E;directChange=true;}
        @Override
        public void run() {
            Random random = new Random();
            while (alive) {
                System.out.println(Thread.currentThread()+" "+getClass().getName());
                int direction = random.nextInt(4);
                switch (direction) {
                    case 0:
                        goUp();
                        break;
                    case 1:
                        goDown();
                        break;
                    case 2:
                        goLeft();
                        break;
                    case 3:
                        goRight();
                        break;
                }
                super.directChange=false;
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
}
