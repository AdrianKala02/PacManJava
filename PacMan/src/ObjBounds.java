import java.awt.image.BufferedImage;

public class ObjBounds {
    FloatRect rect;

    int Height;
    int Width;

    ObjBounds(ObjCreator obj){
        rect=new FloatRect(obj);
        Height=rect.pointPD.getY()-rect.pointPG.getY();
        Width=rect.pointPD.getX()-rect.pointLD.getX();
    }
}
