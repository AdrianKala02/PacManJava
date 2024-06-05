package toolBox;

import objectsForGame.ObjCreator;
import toolBox.ANIAMTIONTYPE;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimateHandler{
    Updater<ImageIcon> updater;
    ObjCreator objCreator;
   private int animateFrames;
   private int currentFrame;
   private boolean directionGoLeft;

   private int size;

   private boolean alive;

    public void stopIt() {
        alive = false;
    }
   ANIAMTIONTYPE aType;
    public AnimateHandler(BufferedImage sheet, ObjCreator objCreator, int updateInterval, ANIAMTIONTYPE aType){
        alive=true;
        size=32;
        currentFrame=0;
        directionGoLeft=true;
        this.aType=aType;
        animateFrames =sheet.getWidth()/size;
        this.objCreator=objCreator;
        objCreator.sprite=objCreator.spriteSheet.getSubimage(0,0,objCreator.getWidth(),objCreator.getHeight());
        objCreator.imageIcon=new ImageIcon(objCreator.sprite);
        updater=new Updater<>(this::animationChooser,objCreator::setImageIcon,updateInterval);
        updater.start();
    }
    private ImageIcon animationChooser(){
        switch (aType){
            case ANIMATIONLOOP: return animationLoop();
            case ANIMATIONPINGPONG: return animationPingPong();
            default: throw new IllegalStateException("Uwaga, podany typ animacji nie istnieje: ->"+aType.name());
        }
    }


    private ImageIcon animationLoop(){
        if(currentFrame>=animateFrames)currentFrame=0;
        BufferedImage sprite=objCreator.spriteSheet.getSubimage(objCreator.getHeight()*(currentFrame+(objCreator.directChange?1:0)),objCreator.direction.getDegreee()*size,objCreator.getHeight(),objCreator.getHeight());
        ImageIcon img= new ImageIcon(sprite.getScaledInstance(objCreator.getScaledX(),objCreator.getScaledY(),Image.SCALE_SMOOTH));
        currentFrame++;
        return img;
    }
    private ImageIcon animationPingPong(){
        /*begin*///making ping pong
        if (directionGoLeft) {
            currentFrame += 1;
            if (currentFrame == animateFrames-1){
                directionGoLeft=false;
            }}
        else currentFrame -= 1;

        if (currentFrame == 0) {directionGoLeft=true;}
        /*end*/
        BufferedImage sprite=objCreator.spriteSheet.getSubimage(objCreator.getHeight()*(currentFrame+(objCreator.directChange?1:0)),objCreator.direction.getDegreee()*size,objCreator.getHeight(),objCreator.getHeight());
        ImageIcon img= new ImageIcon(sprite.getScaledInstance(objCreator.getScaledX(),objCreator.getScaledY(),Image.SCALE_SMOOTH));
        return img;
    }
}
