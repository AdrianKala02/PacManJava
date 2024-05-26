import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Hero implements KeyListener{
    ImageIcon imageIcon;
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


    Hero() {
        posX=0;
        posY=0;
        aclelerationX=0;
        aclelerationY=0;
        speedX=0;
        speedY=0;
        imageIcon = new ImageIcon("/Users/adriankala/Desktop/PacManAsets/PacMan/pk1.png");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
            if(e.getKeyChar()=='w'){aclelerationY=1;}
            if(e.getKeyChar()=='s'){aclelerationY=-1;}
            if(e.getKeyChar()=='a'){aclelerationX=-1;}
            if(e.getKeyChar()=='d'){aclelerationX=1;}
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}