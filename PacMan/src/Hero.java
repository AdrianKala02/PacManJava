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

    public int getPonkty() { return ponkty; }
    public void setPonkty(int ponkty) { this.ponkty = ponkty; }
    public void addPonkty(int ponkty) { this.ponkty += ponkty; }
    private int ponkty;
    private int posX;
    private int posY;
    private int aclelerationX;
    private int aclelerationY;
    private int speedX;
    private int speedY;

    private boolean alive;
    Character[][] gritCharMap;

    public boolean isAlive() {
        return alive;
    }

    public void stopIt(boolean alive) {
        this.alive = alive;
    }

    Hero(String url, ColorRGB mapIdColor, Character idChar) {
        super(url,mapIdColor,idChar);
        posX=0;
        posY=0;
        aclelerationX=0;
        aclelerationY=0;
        speedX=0;
        speedY=0;
        ponkty=0;
        alive=true;
    }

    public void update(){
        System.out.println(posX+" "+posY);
        posX=(posX+aclelerationX);
        posY=(posY+aclelerationY);
    }

    public void updatePos(Character[][] gritCharMap){
        this.gritCharMap=gritCharMap;
        int oldX =posX;
        int oldY = posY;

        posX=(posX+aclelerationX);
        posY=(posY+aclelerationY);

        int newX =posX;
        int newY = posY;

        if (gritCharMap[newY][newX] != 'B') {

            if(gritCharMap[newY][newX] == 'P'){
                ponkty+=1;
            }
            gritCharMap[oldY][oldX] = 'X';
            gritCharMap[newY][newX] = getIdChar();
        } else {
            posX=oldX;
            posY=oldY;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        //dodać boolean dla ruchu aby postać mogła poruszać się tylko w lewo prawo albo góra dół
            if(e.getKeyChar()=='w'||e.getKeyCode()==KeyEvent.VK_UP){aclelerationY=-1;aclelerationX=0;super.direction=DIRECTION.N;directChange=true;}
           else if(e.getKeyChar()=='s'||e.getKeyCode()==KeyEvent.VK_DOWN){aclelerationY=1;aclelerationX=0;super.direction=DIRECTION.S;directChange=true;}
            if(e.getKeyChar()=='a'||e.getKeyCode()==KeyEvent.VK_LEFT){aclelerationX=-1;aclelerationY=0;super.direction=DIRECTION.W;directChange=true;}
           else if(e.getKeyChar()=='d'||e.getKeyCode()==KeyEvent.VK_RIGHT){aclelerationX=1;aclelerationY=0;super.direction=DIRECTION.E;directChange=true;}

           if(e.getKeyCode()==KeyEvent.VK_SPACE){aclelerationX=0;aclelerationY=0;}
           super.directChange=false;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}