package objectsForGame;

import objectsForGame.ObjCreator;
import toolBox.ColorRGB;
import toolBox.DIRECTION;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Hero extends ObjCreator implements KeyListener{
    private int ponkty;
    private int zycia;
    private int posX;
    private int posY;
    private int aclelerationX;
    private int aclelerationY;
    private volatile boolean coverToDmg;
    private int speed;
    private int iniciatedSpeed;
    private int startPosX;
    private int startPosY;
    public int getPosX() {return posX;}
    public void setPosX(int posX) {this.posX = posX;}
    public int getPosY() {return posY;}
    public void setPosY(int posY) {this.posY = posY;}
    public int getAclelerationX() {return aclelerationX;}
    public void setAclelerationX(int aclelerationX) {this.aclelerationX = aclelerationX;}
    public int getAclelerationY() {return aclelerationY;}
    public void setAclelerationY(int aclelerationY) {this.aclelerationY = aclelerationY;}
    public int getPonkty() { return ponkty; }
    public void setPonkty(int ponkty) { this.ponkty = ponkty; }
    public void addPonkty(int ponkty) { this.ponkty += ponkty; }
    public int getZycia() {return zycia;}
    public void setZycia(int zycia) {this.zycia = zycia;}
    public void addZycia(int zycia){this.zycia+=zycia;}
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public boolean isCoverToDmg() {
        return coverToDmg;
    }
    public void setCoverToDmg(boolean coverToDmg) {
        this.coverToDmg = coverToDmg;
    }
    public int getIniciatedSpeed() {
        return iniciatedSpeed;
    }
    public void setIniciatedSpeed(int iniciatedSpeed) {
        this.iniciatedSpeed = iniciatedSpeed;
    }
    public int getStartPosX() {return startPosX;}
    public void setStartPosX(int startPosX) {this.startPosX = startPosX;}
    public int getStartPosY() {return startPosY;}
    public void setStartPosY(int startPosY) {this.startPosY = startPosY;}

    public Hero(String url, ColorRGB mapIdColor, Character idChar) {
        super(url,mapIdColor,idChar);
        posX=0;
        posY=0;
        aclelerationX=0;
        aclelerationY=0;
        ponkty=0;
        zycia=10;
        iniciatedSpeed=250;
        speed=iniciatedSpeed;
        coverToDmg=false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //dodać boolean dla ruchu aby postać mogła poruszać się tylko w lewo prawo albo góra dół
            if(e.getKeyChar()=='w'||e.getKeyCode()==KeyEvent.VK_UP){System.out.println("W");aclelerationY=-1;aclelerationX=0;super.setDirection(DIRECTION.N);}
           else if(e.getKeyChar()=='s'||e.getKeyCode()==KeyEvent.VK_DOWN){System.out.println("S");aclelerationY=1;aclelerationX=0;super.setDirection(DIRECTION.S);}
            if(e.getKeyChar()=='a'||e.getKeyCode()==KeyEvent.VK_LEFT){aclelerationX=-1;System.out.println("A");aclelerationY=0;super.setDirection(DIRECTION.W);}
           else if(e.getKeyChar()=='d'||e.getKeyCode()==KeyEvent.VK_RIGHT){aclelerationX=1;System.out.println("D");aclelerationY=0;super.setDirection(DIRECTION.E);}

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}