import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimateHandler{
    Updater<BufferedImage> updater;
    ObjCreator objCreator;
   private int animateFrames;
   private int currentFrame;
   private boolean directionGoLeft;

   private int size;

   ANIAMTIONTYPE aType;
    AnimateHandler(BufferedImage sheet,ObjCreator objCreator,int updateInterval,ANIAMTIONTYPE aType){
        size=32;
        currentFrame=0;
        directionGoLeft=true;
        this.aType=aType;
        animateFrames =sheet.getWidth()/size;
        //                              ^
        //                              |
        ///NIE UDAŁO SIĘ ZAŁATWIĆ OD RĘKI BARDZIEJ GENERYCZEGO DOBIERANIA OBRAZÓW, POJEDYŃCZY PNG MUSI BYĆ 32
//        System.out.println("szerokość: "+sheet.getWidth()+"wysokość: "+sheet.getHeight());
//        System.out.println("ilość klatek: "+animateFrames);
        this.objCreator=objCreator;
        objCreator.sprite=objCreator.spriteSheet.getSubimage(0,0,objCreator.getWidth(),objCreator.getHeight());
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
        BufferedImage sprite=objCreator.spriteSheet.getSubimage(objCreator.getHeight()*(currentFrame+(objCreator.directChange?1:0)),objCreator.direction.getDegreee()*size,objCreator.getHeight(),objCreator.getHeight());
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
        BufferedImage sprite=objCreator.spriteSheet.getSubimage(objCreator.getHeight()*(currentFrame+(objCreator.directChange?1:0)),objCreator.direction.getDegreee()*size,objCreator.getHeight(),objCreator.getHeight());
        return sprite;
    }
}
