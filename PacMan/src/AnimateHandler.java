import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimateHandler{
    Updater<BufferedImage> updater;
    ObjCreator objCreator;
   private int animateFrames;
   private int currentFrame;
   private boolean directionGoLeft;

   ANIAMTIONTYPE aType;
    AnimateHandler(BufferedImage sheet,ObjCreator objCreator,int updateInterval,ANIAMTIONTYPE aType){
        currentFrame=0;
        directionGoLeft=true;
        this.aType=aType;
        animateFrames =sheet.getWidth()/sheet.getHeight();
//        System.out.println("szerokość: "+sheet.getWidth()+"wysokość: "+sheet.getHeight());
//        System.out.println("ilość klatek: "+animateFrames);
        this.objCreator=objCreator;
        objCreator.sprite=objCreator.spriteSheet.getSubimage(0,0,objCreator.spriteSheet.getHeight(),objCreator.spriteSheet.getHeight());
        updater=new Updater<>(this::animationChooser,objCreator::setSprite,updateInterval);
        updater.start();
    }
    private BufferedImage animationChooser(){
        switch (aType){
            case ANIMATIONLOOP: return animationLoop();
            case ANIMATIONPINGPONG: return animationPingPong();
            default: throw new IllegalStateException("Uwaga, podany typ animacji nie istnieje: ->"+aType.name());
        }
    }
    private BufferedImage animationLoop(){
        if(currentFrame>=animateFrames)currentFrame=0;
        BufferedImage sprite=objCreator.spriteSheet.getSubimage(objCreator.spriteSheet.getHeight()*currentFrame,0,objCreator.spriteSheet.getHeight(),objCreator.spriteSheet.getHeight());
        currentFrame++;
        return sprite;
    }
    private BufferedImage animationPingPong(){
        /*begin*///making ping pong
        if (directionGoLeft) {
            currentFrame += 1;
            if (currentFrame == animateFrames-1){
                directionGoLeft=false;
            }}
        else currentFrame -= 1;

        if (currentFrame == 0) {directionGoLeft=true;}
        /*end*/
        BufferedImage sprite=objCreator.spriteSheet.getSubimage(objCreator.spriteSheet.getHeight()*currentFrame,0,objCreator.spriteSheet.getHeight(),objCreator.spriteSheet.getHeight());
        return sprite;
    }
}
