import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ObjCreator implements Runnable{

    BufferedImage spriteSheet;
    BufferedImage sprite;
    DIRECTION direction;

    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    ObjCreator(String url){
        width=32;
        height=32;
        direction=DIRECTION.E;
            try {
                spriteSheet = ImageIO.read(new File(url));
                //use heigh to X because my arts are 32x32 but in sprite sheet have few imgs so more wight than height
                sprite=spriteSheet.getSubimage(0,0,width,height);

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
