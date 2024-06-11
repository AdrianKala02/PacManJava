package objectsForGame;

import toolBox.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ObjCreator{
    private BufferedImage spriteSheet;
    private BufferedImage sprite;
    private DIRECTION direction;
    private ColorRGB mapIdColor;
    private int posX;
    private int posY;
    private int width;
    private int height;
    private boolean directChange;
    private Character idChar;
    private String url;
    private int scaledX;
    private int scaledY;
    private ImageIcon imageIcon;
    public Character getIdChar() {return idChar;}
    public void setIdChar(Character idChar) {this.idChar = idChar;}
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
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
    public int getScaledX() {
        return scaledX;
    }
    public void setScaledX(int scaledX) {
        this.scaledX = scaledX;
    }
    public int getScaledY() {
        return scaledY;
    }
    public void setScaledY(int scaledY) {
        this.scaledY = scaledY;
    }
    public BufferedImage getSpriteSheet() {return spriteSheet;}
    public void setSpriteSheet(BufferedImage spriteSheet) {this.spriteSheet = spriteSheet;}
    public BufferedImage getSprite() {return sprite;}
    public DIRECTION getDirection() {return direction;}
    public void setDirection(DIRECTION direction) {this.direction = direction;}
    public ColorRGB getMapIdColor() {return mapIdColor;}
    public void setMapIdColor(ColorRGB mapIdColor) {this.mapIdColor = mapIdColor;}
    public boolean isDirectChange() {return directChange;}
    public void setDirectChange(boolean directChange) {this.directChange = directChange;}
    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}
    public ImageIcon getImageIcon() {return imageIcon;}
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }
    public void setImageIcon(ImageIcon imageIcon){this.imageIcon=imageIcon;}
    public ObjCreator(String url,ColorRGB mapIdColor,Character idChar){
        this.url=url;
        this.mapIdColor=mapIdColor;
        this.idChar=idChar;
        scaledX=32;
        scaledY=32;
        posX=0;
        posY=0;
        width=32;
        height=32;
        direction= DIRECTION.E;
        directChange=false;
            try {
                spriteSheet = ImageIO.read(new File(url));
                sprite=spriteSheet.getSubimage(0,0,width,height);
                imageIcon= new ImageIcon(sprite);
            }catch (IOException e){
                e.printStackTrace();
            }


    }
}
