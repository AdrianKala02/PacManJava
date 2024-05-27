import javax.swing.*;
import java.awt.*;
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
    public int getSpeedX() {return speedX;}
    public void setSpeedX(int speedX) {this.speedX = speedX;}
    public int getSpeedY() {return speedY;}
    public void setSpeedY(int speedY) {this.speedY = speedY;}
    private int posX;
    private int posY;
    private int aclelerationX;
    private int aclelerationY;
    private int speedX;
    private int speedY;

    Hero(String url) {
        super(url);
        posX=0;
        posY=0;
        aclelerationX=0;
        aclelerationY=0;
        speedX=0;
        speedY=0;
    }

    public void update(){
        posX=(posX+aclelerationX);
        posY=(posY+aclelerationY);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        //dodać boolean dla ruchu aby postać mogła poruszać się tylko w lewo prawo albo góra dół
            if(e.getKeyChar()=='w'||e.getKeyCode()==KeyEvent.VK_UP){aclelerationY=-1;aclelerationX=0;}
           else if(e.getKeyChar()=='s'||e.getKeyCode()==KeyEvent.VK_DOWN){aclelerationY=1;aclelerationX=0;}
            if(e.getKeyChar()=='a'||e.getKeyCode()==KeyEvent.VK_LEFT){aclelerationX=-1;aclelerationY=0;}
           else if(e.getKeyChar()=='d'||e.getKeyCode()==KeyEvent.VK_RIGHT){aclelerationX=1;aclelerationY=0;}

           if(e.getKeyCode()==KeyEvent.VK_SPACE){aclelerationX=0;aclelerationY=0;}
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}