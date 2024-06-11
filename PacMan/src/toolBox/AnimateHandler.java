package toolBox;

import objectsForGame.ObjCreator;
import toolBox.ANIAMTIONTYPE;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimateHandler{
    private Updater<ImageIcon> updater;
   private ObjCreator objCreator;
   private int animateFrames;
   private int currentFrame;
   private boolean directionGoLeft;
   private int size;
  private ANIAMTIONTYPE aType;
    public AnimateHandler(BufferedImage sheet, ObjCreator objCreator, int updateInterval, ANIAMTIONTYPE aType){
        size=32;
        currentFrame=0;
        directionGoLeft=true;
        this.aType=aType;
        animateFrames =sheet.getWidth()/size;
        this.objCreator=objCreator;
        objCreator.setSprite(objCreator.getSpriteSheet().getSubimage(0,0,objCreator.getWidth(),objCreator.getHeight()));
        objCreator.setImageIcon(new ImageIcon(objCreator.getSprite()));
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
        BufferedImage sprite=objCreator.getSpriteSheet().getSubimage(objCreator.getHeight()*(currentFrame+(objCreator.isDirectChange()?1:0)),objCreator.getDirection().getDegreee()*size,objCreator.getHeight(),objCreator.getHeight());
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
        BufferedImage sprite=objCreator.getSpriteSheet().getSubimage(objCreator.getHeight()*(currentFrame+(objCreator.isDirectChange()?1:0)),objCreator.getDirection().getDegreee()*size,objCreator.getHeight(),objCreator.getHeight());
        ImageIcon img= new ImageIcon(sprite.getScaledInstance(objCreator.getScaledX(),objCreator.getScaledY(),Image.SCALE_SMOOTH));
        return img;
    }
}
