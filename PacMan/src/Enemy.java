import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Enemy extends ObjCreator implements Runnable{
    private int posX;
    private int posY;
    private int aclelerationX;
    private int aclelerationY;
    private boolean alive;
     int posXUnder;
     int posYUnder;
    boolean isUnder;
    char charUnder;

    int posXUnderOLDER;
    int posYUnderOLDER;
    boolean isUnderOLDER;
    char charUnderOLDER;
    Enemy(String url, ColorRGB mapIdColor, Character idChar) {
        super(url, mapIdColor, idChar);
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




    public void goUp(){aclelerationY=-1;aclelerationX=0;super.direction=DIRECTION.N;directChange=true;}
    public void goDown(){aclelerationY=1;aclelerationX=0;super.direction=DIRECTION.S;directChange=true;}
    public void goLeft(){aclelerationX=-1;aclelerationY=0;super.direction=DIRECTION.W;directChange=true;}
    public void goRight(){aclelerationX=1;aclelerationY=0;super.direction=DIRECTION.E;directChange=true;}
        @Override
        public void run() {
            Random random = new Random();
            while (alive) {
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

                //super.directChange=false;
                try {
                    Thread.sleep(1000); // Ruch co sekundę
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
}
