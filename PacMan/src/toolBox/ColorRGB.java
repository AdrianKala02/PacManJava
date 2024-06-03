package toolBox;

public class ColorRGB {


    //simplified removing 3 ints r g b

    //three cosntructors
    private Integer[] pixel;
    ColorRGB(){
            pixel=new Integer[3];
            pixel[0]=0;
            pixel[1]=0;
            pixel[2]=0;
    }
    //gets that strange notUserFriednly value;
   public ColorRGB(int BufferedImageRGB){
         pixel=new Integer[3];
         pixel[2] = BufferedImageRGB & 0xff;
         pixel[1] = (BufferedImageRGB & 0xff00) >> 8;
         pixel[0] = (BufferedImageRGB & 0xff0000) >> 16;
    }
   public ColorRGB(int r,int g,int b){
        pixel=new Integer[3];
        pixel[2] =b;
        pixel[1] =g;
        pixel[0] =r;
    }
   public boolean compareTo(ColorRGB other){
        return (other.getBlue()==pixel[2]&&
                other.getGreen()==pixel[1]&&
                other.getRed()==pixel[0]);
    }
    public void setPixel(Integer[] pixel){this.pixel=pixel;}
    public Integer[] getPixel(){return pixel;}
    public int getRed(){return pixel[0];}
    public int getGreen(){return pixel[1];}
    public int getBlue(){return pixel[2];}
    public void setRed(int red) {pixel[0] = red;}
    public void setGreen(int green) {pixel[1] = green;}
    public void setBlue(int blue) {pixel[2] = blue;}
}
