import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ObjCreator implements Runnable{

    BufferedImage spriteSheet;
    BufferedImage sprite;
    ObjCreator(String url){
            try {
                spriteSheet = ImageIO.read(new File(url));
                //use heigh to X because my arts are 32x32 but in sprite sheet have few imgs so more wight than height
                sprite=spriteSheet.getSubimage(0,0,spriteSheet.getHeight(),spriteSheet.getHeight());

            }catch (IOException e){
                e.printStackTrace();
            }
    }

    @Override
    public void run() {

    }
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }
}
