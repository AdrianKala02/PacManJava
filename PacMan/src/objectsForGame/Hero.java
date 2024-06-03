package objectsForGame;

import objectsForGame.ObjCreator;
import toolBox.ColorRGB;
import toolBox.DIRECTION;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Hero extends ObjCreator implements KeyListener{
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
    private int ponkty;
    private int zycia;
    public int getZycia() {return zycia;}
    public void setZycia(int zycia) {this.zycia = zycia;}
    public void addZycia(int zycia){this.zycia+=zycia;}
    private int posX;
    private int posY;
    private int aclelerationX;
    private int aclelerationY;


    public Hero(String url, ColorRGB mapIdColor, Character idChar) {
        super(url,mapIdColor,idChar);
        posX=0;
        posY=0;
        aclelerationX=0;
        aclelerationY=0;
        ponkty=0;
        zycia=3;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //dodać boolean dla ruchu aby postać mogła poruszać się tylko w lewo prawo albo góra dół
            if(e.getKeyChar()=='w'||e.getKeyCode()==KeyEvent.VK_UP){aclelerationY=-1;aclelerationX=0;super.direction= DIRECTION.N;}
           else if(e.getKeyChar()=='s'||e.getKeyCode()==KeyEvent.VK_DOWN){aclelerationY=1;aclelerationX=0;super.direction= DIRECTION.S;}
            if(e.getKeyChar()=='a'||e.getKeyCode()==KeyEvent.VK_LEFT){aclelerationX=-1;aclelerationY=0;super.direction= DIRECTION.W;}
           else if(e.getKeyChar()=='d'||e.getKeyCode()==KeyEvent.VK_RIGHT){aclelerationX=1;aclelerationY=0;super.direction= DIRECTION.E;}

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}