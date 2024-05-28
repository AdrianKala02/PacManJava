import java.awt.image.BufferedImage;

public class ObjBounds {
    FloatRect rect;

    ObjBounds(ObjCreator obj){
        rect=new FloatRect(obj);
    }
}
