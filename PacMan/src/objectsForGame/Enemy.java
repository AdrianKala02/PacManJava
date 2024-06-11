package objectsForGame;

import toolBox.ColorRGB;
import toolBox.DIRECTION;

import java.nio.channels.Pipe;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Enemy extends ObjCreator implements Runnable{
    private int posX;
    private int posY;
    private int aclelerationX;
    private int aclelerationY;
    private boolean alive;
    private boolean isUnder;
    private char charUnder;

   private int startPosX;

   private int oldPosX;
   private int oldPosY;

    public int getOldPosX() {
        return oldPosX;
    }

    public void setOldPosX(int oldPosX) {
        this.oldPosX = oldPosX;
    }

    public int getOldPosY() {
        return oldPosY;
    }

    public void setOldPosY(int oldPosY) {
        this.oldPosY = oldPosY;
    }

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

    public int getSpeedToChangeDirection() {
        return speedToChangeDirection;
    }

    public void setSpeedToChangeDirection(int speedToChangeDirection) {
        this.speedToChangeDirection = speedToChangeDirection;
    }

    private int speedToChangeDirection;
    private int iniciatedSpeedToChangeDirection;

    public int getIniciatedSpeedToChangeDirection() {
        return iniciatedSpeedToChangeDirection;
    }

    public void setIniciatedSpeedToChangeDirection(int iniciatedSpeedToChangeDirection) {
        this.iniciatedSpeedToChangeDirection = iniciatedSpeedToChangeDirection;
    }

    public Enemy(String url, ColorRGB mapIdColor, Character idChar) {
        super(url, mapIdColor, idChar);
        isUnder=false;
        posX=0;
        posY=0;
        aclelerationX=0;
        aclelerationY=0;
        alive=true;
        iniciatedSpeedToChangeDirection=300;
        speedToChangeDirection=iniciatedSpeedToChangeDirection;
        dropThatBomb=false;
    }
    public Enemy(Enemy en){
        super(en.getUrl(),en.getMapIdColor(),en.getIdChar());

        isUnder=en.isUnder;
        posX=en.getPosX();
        posY=en.getPosY();
        aclelerationX=en.getAclelerationX();
        aclelerationY=en.aclelerationY;
        alive=en.alive;
        startPosX=en.startPosX;
        startPosY=en.startPosY;
        charUnder=en.charUnder;
        iniciatedSpeedToChangeDirection=en.iniciatedSpeedToChangeDirection;
        speedToChangeDirection=en.speedToChangeDirection;
        dropThatBomb=en.dropThatBomb;
    }
        public int getPosX() {return posX;}
        public void setPosX(int posX) {this.posX = posX;}
        public int getPosY() {return posY;}
        public void setPosY(int posY) {this.posY = posY;}
        public int getAclelerationX() {return aclelerationX;}
        public void setAclelerationX(int aclelerationX) {this.aclelerationX = aclelerationX;}
        public int getAclelerationY() {return aclelerationY;}
        public void setAclelerationY(int aclelerationY) {this.aclelerationY = aclelerationY;}
        public boolean isAlive() {
            return alive;
        }
        public void stopIt() {alive=false;}

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isUnder() {
        return isUnder;
    }

    public void setUnder(boolean under) {
        isUnder = under;
    }

    public char getCharUnder() {
        return charUnder;
    }

    public void setCharUnder(char charUnder) {
        this.charUnder = charUnder;
    }

    public boolean isDropThatBomb() {
        return dropThatBomb;
    }

    public void setDropThatBomb(boolean dropThatBomb) {
        this.dropThatBomb = dropThatBomb;
    }

    private boolean dropThatBomb;


    //F - freez
    //A - slowThink
    //T - goHome
    //S - shield
    //Q - speedster
    public char getGift(){
        char rr = 0;
            Random random = new Random();
            random.nextInt(5);
            switch (random.nextInt(5)){
                case 0:
                    rr='F';
                    break;
                case 1:
                    rr='A';
                    break;
                case 2:
                    rr='T';
                    break;
                case 3:
                    rr='S';
                    break;
                case 4:
                    rr='Q';
                    break;
            };

            return rr;
    }
    public void goUp(){aclelerationY=-1;aclelerationX=0;super.setDirection(DIRECTION.N);setDirectChange(true);}
    public void goDown(){aclelerationY=1;aclelerationX=0;super.setDirection(DIRECTION.S);setDirectChange(true);}
    public void goLeft(){aclelerationX=-1;aclelerationY=0;super.setDirection(DIRECTION.W);setDirectChange(true);}
    public void goRight(){aclelerationX=1;aclelerationY=0;super.setDirection(DIRECTION.E);setDirectChange(true);}
        @Override
        public void run() {
        Thread randomPower= new Thread(()->{
            Random random = new Random();
            while (alive){
                int chanceToDropPower = random.nextInt(4);
                System.out.println("asdasd: "+chanceToDropPower);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(chanceToDropPower==0&&!isUnder){
                    //make a power
                    dropThatBomb=true;
                }
            }
        });
        randomPower.start();
            Random random = new Random();
            while (alive) {
                //!// System.out.println(Thread.currentThread()+" "+getClass().getName());
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
                super.setDirectChange(false);
                try {
                    Thread.sleep(speedToChangeDirection);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
}
