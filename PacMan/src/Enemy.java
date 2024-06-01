import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Enemy extends ObjCreator{
    private int posX;
    private int posY;
    private int aclelerationX;
    private int aclelerationY;
    private boolean alive;
    Enemy(String url, ColorRGB mapIdColor, Character idChar) {
        super(url, mapIdColor, idChar);
        posX=0;
        posY=0;
        aclelerationX=0;
        aclelerationY=0;
        alive=true;
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
        gritCharMap[oldY][oldX] = 'X';
        if (gritCharMap[newY][newX] == 'P') {
            gritCharMap[oldY][oldX] = 'P';
        }

        gritCharMap[newY][newX] = getIdChar();
        setPosX(newX);
        setPosY(newY);
    } else {
        setPosX(oldX);
        setPosY(oldY);
    }
    }
//public void updatePos() {
//    int oldX = getPosX();
//    int oldY = getPosY();
//
//    int newX = oldX + getAclelerationX();
//    int newY = oldY + getAclelerationY();
//
//    if (gritCharMap[newY][newX] != 'B') {
//        gritCharMap[oldY][oldX] = 'X';
//        if (gritCharMap[newY][newX] == 'P') {
//            gritCharMap[oldY][oldX] = 'P';
//        }
//
//        gritCharMap[newY][newX] = getIdChar();
//        setPosX(newX);
//        setPosY(newY);
//    } else {
//        setPosX(oldX);
//        setPosY(oldY);
//    }
//}

        public void move(KeyEvent e) {


//            //dodać boolean dla ruchu aby postać mogła poruszać się tylko w lewo prawo albo góra dół
            if(e.getKeyChar()=='t'||e.getKeyCode()==KeyEvent.VK_UP){
                System.out.println("SKDLAJDLAKSDJHALSKDJHALSDKJADS"); aclelerationY=-1;aclelerationX=0;super.direction=DIRECTION.N;directChange=true;}
//            else if(e.getKeyChar()=='s'||e.getKeyCode()==KeyEvent.VK_DOWN){aclelerationY=1;aclelerationX=0;super.direction=DIRECTION.S;directChange=true;}
//            if(e.getKeyChar()=='a'||e.getKeyCode()==KeyEvent.VK_LEFT){aclelerationX=-1;aclelerationY=0;super.direction=DIRECTION.W;directChange=true;}
//            else if(e.getKeyChar()=='d'||e.getKeyCode()==KeyEvent.VK_RIGHT){aclelerationX=1;aclelerationY=0;super.direction=DIRECTION.E;directChange=true;}
//
//            if(e.getKeyCode()==KeyEvent.VK_SPACE){aclelerationX=0;aclelerationY=0;}
//            super.directChange=false;
        }
}
