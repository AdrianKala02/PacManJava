public class FloatRect {
    Point pointLG;
    Point pointPG;
    Point pointLD;
    Point pointPD;

    FloatRect(ObjCreator obj){
        pointLG=new Point(obj.getPosX(), obj.getPosY());
        pointPG=new Point(obj.getPosX()+ obj.getWidth(), obj.getPosY());
        pointLD=new Point(obj.getPosX(),obj.getPosY()+ obj.getHeight());
        pointPD=new Point(obj.getPosX()+ obj.getWidth(),obj.getPosY()+ obj.getHeight());
    }
}
