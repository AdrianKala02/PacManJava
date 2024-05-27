import java.awt.image.BufferedImage;

public class AnimateHandler {
    int AnimateFrames;
    AnimateHandler(BufferedImage sheet,ObjCreator objCreator){
       AnimateFrames =sheet.getWidth()/sheet.getHeight();
       objCreator.sprite=objCreator.spriteSheet.getSubimage()

    }
}
