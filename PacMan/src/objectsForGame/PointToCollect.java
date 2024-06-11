package objectsForGame;

import toolBox.ColorRGB;

public class PointToCollect extends ObjCreator {
   private int valueOfPoint;
   public int getValueOfPoint(){
       return valueOfPoint;
   }
    public PointToCollect(int valueOfPoint, String url, ColorRGB mapIdColor, Character idChar) {
        super(url, mapIdColor, idChar);
        this.valueOfPoint=valueOfPoint;
    }
}
